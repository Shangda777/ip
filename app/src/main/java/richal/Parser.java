package richal;

import java.util.List;

/**
 * Parses user input and executes the corresponding commands.
 */
public class Parser {

    /**
     * Parses and executes a user command.
     *
     * @param input the user input
     * @param taskList the task list
     * @param ui the UI object
     * @param storage the storage object
     * @return true if the program should exit, false otherwise
     * @throws DukeException if the command is invalid
     */
    public static boolean parse(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        // Check if user typed "bye" to exit the program
        if (input.equals("bye")) {
            ui.showGoodbye();
            return true;
        }

        // Check if user typed "list" to list all tasks
        if (input.equals("list")) {
            ui.showTaskList(taskList);
            ui.showTaskCount(taskList.getSize());
            return false;
        }

        // Check if user typed "find <keyword>" to search for tasks
        if (input.startsWith("find ")) {
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new DukeException("Please provide a keyword to search for.");
            }
            List<Task> matching = taskList.findTasks(keyword);
            ui.showMatchingTasks(matching);
            return false;
        }

        // Check if user typed "mark <number>" to mark a task as done
        if (input.startsWith("mark ")) {
            int index = parseIndex(input.substring(5).trim(), taskList.getSize());
            taskList.getTask(index).markDone();
            ui.showTaskMarked(taskList.getTask(index));
            ui.showTaskCount(taskList.getSize());
            saveToStorage(taskList, storage, ui);
            return false;
        }

        // Check if user typed "unmark <number>" to mark a task as not done
        if (input.startsWith("unmark ")) {
            int index = parseIndex(input.substring(7).trim(), taskList.getSize());
            taskList.getTask(index).markUndone();
            ui.showTaskUnmarked(taskList.getTask(index));
            ui.showTaskCount(taskList.getSize());
            saveToStorage(taskList, storage, ui);
            return false;
        }

        // Check if user typed "delete <number>" to delete a task
        if (input.startsWith("delete ")) {
            int index = parseIndex(input.substring(7).trim(), taskList.getSize());
            Task deletedTask = taskList.getTask(index);
            taskList.deleteTask(index);
            ui.showTaskDeleted(deletedTask);
            ui.showTaskCount(taskList.getSize());
            saveToStorage(taskList, storage, ui);
            return false;
        }

        // Check if user typed "todo <description>" to add a todo task
        if (input.startsWith("todo")) {
            String desc = input.length() > 4 ? input.substring(5).trim() : "";
            if (desc.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Task task = new Todo(desc);
            taskList.addTask(task);
            ui.showTaskAdded(task, taskList.getSize());
            ui.showTaskCount(taskList.getSize());
            saveToStorage(taskList, storage, ui);
            return false;
        }

        // Check if user typed "deadline <description> /by <date>" to add a deadline task
        if (input.startsWith("deadline")) {
            String[] parts = input.length() > 8 ? input.substring(8).trim().split("/by") : new String[0];
            if (parts.length != 2) {
                throw new DukeException("The description of a deadline must contain a /by date.");
            }
            try {
                Task task = new Deadline(parts[0].trim(), parts[1].trim());
                taskList.addTask(task);
                ui.showTaskAdded(task, taskList.getSize());
                ui.showTaskCount(taskList.getSize());
                saveToStorage(taskList, storage, ui);
            } catch (Exception e) {
                throw new DukeException("Invalid date/time format. Supported formats:\n"
                    + "  - 2/12/2019 1800 or 2/12/2019 18:00\n"
                    + "  - 2019-12-02 1800 or 2019-12-02 18:00\n"
                    + "  - 2/12/2019 (defaults to 00:00)");
            }
            return false;
        }

        // Check if user typed "event <description> /from <date> /to <date>" to add an event task
        if (input.startsWith("event")) {
            String[] parts = input.length() > 6 ? input.substring(6).trim().split("/from") : new String[0];
            if (parts.length != 2) {
                throw new DukeException("The description of an event must contain a /from date.");
            }
            String[] toParts = parts[1].length() > 3 ? parts[1].trim().split("/to") : new String[0];
            if (toParts.length != 2) {
                throw new DukeException("The description of an event must contain a /to date.");
            }
            try {
                Task task = new Event(parts[0].trim(), toParts[0].trim(), toParts[1].trim());
                taskList.addTask(task);
                ui.showTaskAdded(task, taskList.getSize());
                ui.showTaskCount(taskList.getSize());
                saveToStorage(taskList, storage, ui);
            } catch (Exception e) {
                throw new DukeException("Invalid date/time format. Supported formats:\n"
                    + "  - 2/12/2019 1800 or 2/12/2019 18:00\n"
                    + "  - 2019-12-02 1800 or 2019-12-02 18:00\n"
                    + "  - 2/12/2019 (defaults to 00:00)");
            }
            return false;
        }

        // If none of the above, throw an exception
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Parses a string to an integer and checks if it is a valid index.
     *
     * @param s the string to parse
     * @param size the size of the list
     * @return the index
     * @throws DukeException if the string is not a valid integer or the index is out of range
     */
    private static int parseIndex(String s, int size) throws DukeException {
        try {
            int number = Integer.parseInt(s);
            int idx = number - 1;
            if (idx < 0 || idx >= size) {
                throw new DukeException("That task number is out of range.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number.");
        }
    }

    /**
     * Saves the task list to storage.
     *
     * @param taskList the task list to save
     * @param storage the storage object
     * @param ui the UI object
     */
    private static void saveToStorage(TaskList taskList, Storage storage, Ui ui) {
        try {
            storage.save(taskList.getAllTasks());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}

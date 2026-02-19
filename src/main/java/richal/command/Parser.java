package richal.command;

import java.util.List;

import richal.DukeException;
import richal.storage.Storage;
import richal.task.Deadline;
import richal.task.Event;
import richal.task.Task;
import richal.task.TaskList;
import richal.task.Todo;
import richal.ui.Ui;

/**
 * Parses user input and executes the corresponding commands.
 */
public class Parser {

    private static final String DATE_FORMAT_HELP = "Invalid date/time format. Supported formats:\n"
            + "  - 2/12/2019 1800 or 2/12/2019 18:00\n"
            + "  - 2019-12-02 1800 or 2019-12-02 18:00\n"
            + "  - 2/12/2019 (defaults to 00:00)";

    /**
     * Parses a user command and returns the response string.
     *
     * @param input    the raw user input
     * @param taskList the current task list
     * @param ui       the UI for building response messages
     * @param storage  the storage for persistence
     * @return the response to display
     * @throws DukeException if the command is invalid or malformed
     */
    public static String parseAndGetResponse(String input, TaskList taskList, Ui ui,
            Storage storage) throws DukeException {
        assert input != null : "User input should not be null";
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (input.equals("bye")) {
            return ui.getGoodbyeMessage();
        }
        if (input.equals("list")) {
            return ui.getTaskListMessage(taskList);
        }
        if (input.startsWith("find ")) {
            return handleFind(input, taskList, ui);
        }
        if (input.startsWith("mark ")) {
            return handleMark(input, taskList, ui, storage);
        }
        if (input.startsWith("unmark ")) {
            return handleUnmark(input, taskList, ui, storage);
        }
        if (input.startsWith("delete ")) {
            return handleDelete(input, taskList, ui, storage);
        }
        if (input.startsWith("todo")) {
            return handleTodo(input, taskList, ui, storage);
        }
        if (input.startsWith("deadline")) {
            return handleDeadline(input, taskList, ui, storage);
        }
        if (input.startsWith("event")) {
            return handleEvent(input, taskList, ui, storage);
        }

        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    private static String handleFind(String input, TaskList taskList, Ui ui) throws DukeException {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new DukeException("Please provide a keyword to search for.");
        }
        List<Task> matching = taskList.findTasks(keyword);
        return ui.getMatchingTasksMessage(matching);
    }

    private static String handleMark(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = parseIndex(input.substring(5).trim(), taskList.getSize());
        taskList.getTask(index).markDone();
        saveToStorage(taskList, storage);
        return ui.getTaskMarkedMessage(taskList.getTask(index));
    }

    private static String handleUnmark(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = parseIndex(input.substring(7).trim(), taskList.getSize());
        taskList.getTask(index).markUndone();
        saveToStorage(taskList, storage);
        return ui.getTaskUnmarkedMessage(taskList.getTask(index));
    }

    private static String handleDelete(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = parseIndex(input.substring(7).trim(), taskList.getSize());
        Task deletedTask = taskList.getTask(index);
        taskList.deleteTask(index);
        saveToStorage(taskList, storage);
        return ui.getTaskDeletedMessage(deletedTask, taskList.getSize());
    }

    private static String handleTodo(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String desc = input.length() > 4 ? input.substring(5).trim() : "";
        if (desc.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(desc);
        if (taskList.hasDuplicate(task)) {
            throw new DukeException("This task already exists in your list.");
        }
        taskList.addTask(task);
        saveToStorage(taskList, storage);
        return ui.getTaskAddedMessage(task, taskList.getSize());
    }

    private static String handleDeadline(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.length() > 8 ? input.substring(8).trim().split("/by") : new String[0];
        if (parts.length != 2) {
            throw new DukeException("The description of a deadline must contain a /by date.");
        }
        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
            if (taskList.hasDuplicate(task)) {
                throw new DukeException("This task already exists in your list.");
            }
            taskList.addTask(task);
            saveToStorage(taskList, storage);
            return ui.getTaskAddedMessage(task, taskList.getSize());
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeException(DATE_FORMAT_HELP);
        }
    }

    private static String handleEvent(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException {
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
            if (taskList.hasDuplicate(task)) {
                throw new DukeException("This task already exists in your list.");
            }
            taskList.addTask(task);
            saveToStorage(taskList, storage);
            return ui.getTaskAddedMessage(task, taskList.getSize());
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeException(DATE_FORMAT_HELP);
        }
    }

    private static int parseIndex(String s, int size) throws DukeException {
        assert s != null : "Index string should not be null";
        assert size >= 0 : "Task list size should not be negative";
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

    private static void saveToStorage(TaskList taskList, Storage storage) {
        assert taskList != null : "TaskList should not be null when saving";
        assert storage != null : "Storage should not be null when saving";
        try {
            storage.save(taskList.getAllTasks());
        } catch (DukeException e) {
            // Silently handle save errors
        }
    }
}

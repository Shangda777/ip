package richal;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 * Deals with reading user input and displaying messages.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "--------------------------------";
    private final Scanner scanner;

    /**
     * Creates a Ui object with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Richal");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Displays a loading error message.
     *
     * @param message the error message to display
     */
    public void showLoadingError(String message) {
        System.out.println("Error loading tasks: " + message);
        System.out.println("Starting with empty task list.");
    }

    /**
     * Displays the number of loaded tasks.
     *
     * @param count the number of tasks loaded
     */
    public void showTasksLoaded(int count) {
        System.out.println("Loaded " + count + " tasks from file.");
    }

    /**
     * Displays a message about a task being added.
     *
     * @param task the task that was added
     * @param totalTasks the total number of tasks
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toDisplayString());
    }

    /**
     * Displays a message about a task being marked as done.
     *
     * @param task the task that was marked
     */
    public void showTaskMarked(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toDisplayString());
        showLine();
    }

    /**
     * Displays a message about a task being unmarked.
     *
     * @param task the task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toDisplayString());
        showLine();
    }

    /**
     * Displays a message about a task being deleted.
     *
     * @param task the task that was deleted
     */
    public void showTaskDeleted(Task task) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toDisplayString());
        showLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList the task list to display
     */
    public void showTaskList(TaskList taskList) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i).toDisplayString());
        }
        showLine();
    }

    /**
     * Displays the number of tasks in the list.
     *
     * @param count the number of tasks
     */
    public void showTaskCount(int count) {
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Reads a command from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}

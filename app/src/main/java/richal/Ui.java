package richal;

import java.util.List;

/**
 * Handles interactions with the user.
 * Deals with reading user input and displaying messages.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "--------------------------------";

    /**
     * Returns the welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Richal\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns an error message.
     *
     * @param message the error message
     */
    public String getErrorMessage(String message) {
        return "OOPS!!! " + message;
    }

    /**
     * Returns a message about a task being added.
     *
     * @param task the task that was added
     * @param totalTasks the total number of tasks
     */
    public String getTaskAddedMessage(Task task, int totalTasks) {
        return "Got it. I've added this task:\n" 
            + task.toDisplayString() + "\n"
            + "Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Returns a message about a task being marked as done.
     *
     * @param task the task that was marked
     */
    public String getTaskMarkedMessage(Task task) {
        return "Nice! I've marked this task as done:\n" 
            + task.toDisplayString();
    }

    /**
     * Returns a message about a task being unmarked.
     *
     * @param task the task that was unmarked
     */
    public String getTaskUnmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" 
            + task.toDisplayString();
    }

    /**
     * Returns a message about a task being deleted.
     *
     * @param task the task that was deleted
     * @param remainingTasks the number of remaining tasks
     */
    public String getTaskDeletedMessage(Task task, int remainingTasks) {
        return "Noted. I've removed this task:\n" 
            + task.toDisplayString() + "\n"
            + "Now you have " + remainingTasks + " tasks in the list.";
    }

    /**
     * Returns the list of tasks.
     *
     * @param taskList the task list to display
     */
    public String getTaskListMessage(TaskList taskList) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.getTask(i).toDisplayString()).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns the list of matching tasks from a find search.
     *
     * @param matchingTasks the list of tasks that match the search keyword
     */
    public String getMatchingTasksMessage(List<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(matchingTasks.get(i).toDisplayString()).append("\n");
        }
        return sb.toString().trim();
    }
}

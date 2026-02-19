package richal.ui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import richal.task.Task;
import richal.task.TaskList;

/**
 * Handles user-facing messages.
 */
public class Ui {

    public String getWelcomeMessage() {
        return "Hello! I'm Richal\nWhat can I do for you?";
    }

    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String getErrorMessage(String message) {
        return "OOPS!!! " + message;
    }

    /**
     * Returns a message confirming a task was added.
     *
     * @param task       the newly added task
     * @param totalTasks total number of tasks after addition
     */
    public String getTaskAddedMessage(Task task, int totalTasks) {
        return "Got it. I've added this task:\n"
            + task.toDisplayString() + "\n"
            + "Now you have " + totalTasks + " tasks in the list.";
    }

    public String getTaskMarkedMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toDisplayString();
    }

    public String getTaskUnmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toDisplayString();
    }

    /**
     * Returns a message confirming a task was deleted.
     *
     * @param task           the deleted task
     * @param remainingTasks number of tasks remaining
     */
    public String getTaskDeletedMessage(Task task, int remainingTasks) {
        return "Noted. I've removed this task:\n"
            + task.toDisplayString() + "\n"
            + "Now you have " + remainingTasks + " tasks in the list.";
    }

    /**
     * Returns a numbered list of all tasks.
     *
     * @param taskList the current task list
     */
    public String getTaskListMessage(TaskList taskList) {
        List<Task> tasks = taskList.getAllTasks();
        String items = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toDisplayString())
                .collect(Collectors.joining("\n"));
        return "Here are the tasks in your list:\n" + items;
    }

    /**
     * Returns a numbered list of matching tasks.
     *
     * @param matchingTasks the list of matching tasks
     */
    public String getMatchingTasksMessage(List<Task> matchingTasks) {
        String items = IntStream.range(0, matchingTasks.size())
                .mapToObj(i -> (i + 1) + ". " + matchingTasks.get(i).toDisplayString())
                .collect(Collectors.joining("\n"));
        return "Here are the matching tasks in your list:\n" + items;
    }
}

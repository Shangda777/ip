package richal.ui;

import java.util.List;

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

    public String getTaskDeletedMessage(Task task, int remainingTasks) {
        return "Noted. I've removed this task:\n"
            + task.toDisplayString() + "\n"
            + "Now you have " + remainingTasks + " tasks in the list.";
    }

    public String getTaskListMessage(TaskList taskList) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.getTask(i).toDisplayString()).append("\n");
        }
        return sb.toString().trim();
    }

    public String getMatchingTasksMessage(List<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(matchingTasks.get(i).toDisplayString()).append("\n");
        }
        return sb.toString().trim();
    }
}

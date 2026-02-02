package richal;

/**
 * Represents a task with a description and completion status.
 * Base class for Todo, Deadline and Event.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task with the given description.
     * The task is initially not done.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of this task for display.
     * Format: [status] description, where status is "X" if done, " " if not done.
     */
    public String toDisplayString() {
        return "[" + (isDone ? "X" : " ") + "]" + description;
    }
}


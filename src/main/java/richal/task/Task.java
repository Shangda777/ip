package richal.task;

/**
 * Represents a task with a description and completion status.
 * Base class for Todo, Deadline and Event.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new incomplete task with the given description.
     *
     * @param description the task description
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /** Marks this task as done. */
    public void markDone() {
        this.isDone = true;
    }

    /** Marks this task as not done. */
    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a human-readable representation of this task including its status icon.
     */
    public String toDisplayString() {
        return "[" + (isDone ? "X" : " ") + "]" + description;
    }
}

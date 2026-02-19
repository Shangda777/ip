package richal.task;

/**
 * Represents a task with a description and completion status.
 * Base class for Todo, Deadline and Event.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toDisplayString() {
        return "[" + (isDone ? "X" : " ") + "]" + description;
    }
}

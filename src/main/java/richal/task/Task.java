package richal.task;

import java.util.Objects;

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

    /**
     * Two tasks are equal if they are of the same concrete type and have the same description.
     * Subclasses should override this to also compare type-specific fields (e.g. dates).
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Task task = (Task) other;
        return Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), description);
    }
}

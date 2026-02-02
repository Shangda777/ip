package richal;

/**
 * Represents a todo task with no date or time.
 * Only stores the task description.
 */
public class Todo extends Task {

    /**
     * Creates a todo task with the given description.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo for display.
     * Format: [T][status] description
     */
    @Override
    public String toDisplayString() {
        return "[T]" + super.toDisplayString();
    }
}

package richal;

/**
 * Represents a task with a deadline.
 * Stores the task description and the due date/time (by).
 */
public class Deadline extends Task {

    private String by;

    /**
     * Creates a deadline task with the given description and due date/time.
     *
     * @param description the task description
     * @param by          the due date or time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of this deadline for display.
     * Format: [D][status] description (by: due date/time)
     */
    @Override
    public String toDisplayString() {
        return "[D]" + super.toDisplayString() + " (by: " + by + ")";
    }

    /**
     * Returns the due date/time of this deadline.
     *
     * @return the due date or time
     */
    public String getBy() {
        return by;
    }
}

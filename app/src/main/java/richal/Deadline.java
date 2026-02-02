package richal;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Stores the task description and the due date/time (by).
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Creates a deadline task with the given description and due date/time.
     *
     * @param description the task description
     * @param by          the due date/time as a LocalDateTime
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline task with the given description and due date/time string.
     * Parses the date/time string in various formats.
     *
     * @param description the task description
     * @param byString    the due date/time as a string (multiple formats supported)
     * @throws DateTimeParseException if the date/time string cannot be parsed
     */
    public Deadline(String description, String byString) {
        super(description);
        this.by = DateTimeParser.parse(byString);
    }

    /**
     * Returns a string representation of this deadline for display.
     * Format: [D][status] description (by: MMM dd yyyy, hh:mma)
     */
    @Override
    public String toDisplayString() {
        return "[D]" + super.toDisplayString() + " (by: " + DateTimeParser.formatForDisplay(by) + ")";
    }

    /**
     * Returns the due date/time of this deadline.
     *
     * @return the due date/time as a LocalDateTime
     */
    public LocalDateTime getByDateTime() {
        return by;
    }

    /**
     * Returns the due date/time as a string in storage format.
     * Used for saving to file.
     *
     * @return the due date/time as a string
     */
    public String getBy() {
        return DateTimeParser.formatForStorage(by);
    }
}

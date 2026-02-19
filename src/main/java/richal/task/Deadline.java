package richal.task;

import java.time.LocalDateTime;

import richal.util.DateTimeParser;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Creates a Deadline with a LocalDateTime due date.
     *
     * @param description task description
     * @param by          due date/time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a Deadline by parsing a date/time string.
     *
     * @param description task description
     * @param byString    due date/time string (multiple formats supported)
     */
    public Deadline(String description, String byString) {
        super(description);
        this.by = DateTimeParser.parse(byString);
    }

    @Override
    public String toDisplayString() {
        return "[D]" + super.toDisplayString() + " (by: " + DateTimeParser.formatForDisplay(by) + ")";
    }

    public LocalDateTime getByDateTime() {
        return by;
    }

    /** Returns the due date/time formatted for file storage. */
    public String getBy() {
        return DateTimeParser.formatForStorage(by);
    }
}

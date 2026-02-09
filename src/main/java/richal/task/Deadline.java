package richal.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import richal.util.DateTimeParser;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

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

    public String getBy() {
        return DateTimeParser.formatForStorage(by);
    }
}

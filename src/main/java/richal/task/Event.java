package richal.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import richal.util.DateTimeParser;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String fromString, String toString) {
        super(description);
        this.from = DateTimeParser.parse(fromString);
        this.to = DateTimeParser.parse(toString);
    }

    @Override
    public String toDisplayString() {
        return "[E]" + super.toDisplayString() + " (from: " + DateTimeParser.formatForDisplay(from)
                + " to: " + DateTimeParser.formatForDisplay(to) + ")";
    }

    public LocalDateTime getFromDateTime() {
        return from;
    }

    public LocalDateTime getToDateTime() {
        return to;
    }

    public String getFrom() {
        return DateTimeParser.formatForStorage(from);
    }

    public String getTo() {
        return DateTimeParser.formatForStorage(to);
    }
}

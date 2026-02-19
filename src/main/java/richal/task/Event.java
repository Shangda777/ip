package richal.task;

import java.time.LocalDateTime;
import java.util.Objects;

import richal.util.DateTimeParser;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an Event with explicit LocalDateTime boundaries.
     *
     * @param description task description
     * @param from        start date/time
     * @param to          end date/time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event by parsing date/time strings.
     *
     * @param description task description
     * @param fromString  start date/time string (multiple formats supported)
     * @param toString    end date/time string (multiple formats supported)
     */
    public Event(String description, String fromString, String toString) {
        super(description);
        this.from = DateTimeParser.parse(fromString);
        this.to = DateTimeParser.parse(toString);
    }

    @Override
    public String toDisplayString() {
        return "[E]" + super.toDisplayString()
                + " (from: " + DateTimeParser.formatForDisplay(from)
                + " to: " + DateTimeParser.formatForDisplay(to) + ")";
    }

    public LocalDateTime getFromDateTime() {
        return from;
    }

    public LocalDateTime getToDateTime() {
        return to;
    }

    /** Returns the start date/time formatted for file storage. */
    public String getFrom() {
        return DateTimeParser.formatForStorage(from);
    }

    /** Returns the end date/time formatted for file storage. */
    public String getTo() {
        return DateTimeParser.formatForStorage(to);
    }

    /** Two events are equal if they have the same description, start, and end date/time. */
    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        Event event = (Event) other;
        return Objects.equals(from, event.from) && Objects.equals(to, event.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to);
    }
}

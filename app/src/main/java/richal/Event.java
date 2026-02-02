package richal;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end date/time.
 * Stores the task description, start date/time (from) and end date/time (to).
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an event task with the given description and date/time range.
     *
     * @param description the task description
     * @param from        the start date/time as a LocalDateTime
     * @param to          the end date/time as a LocalDateTime
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an event task with the given description and date/time strings.
     * Parses the date/time strings in various formats.
     *
     * @param description the task description
     * @param fromString  the start date/time as a string (multiple formats supported)
     * @param toString    the end date/time as a string (multiple formats supported)
     * @throws DateTimeParseException if the date/time strings cannot be parsed
     */
    public Event(String description, String fromString, String toString) {
        super(description);
        this.from = DateTimeParser.parse(fromString);
        this.to = DateTimeParser.parse(toString);
    }

    /**
     * Returns a string representation of this event for display.
     * Format: [E][status] description (from: MMM dd yyyy, hh:mma to: MMM dd yyyy, hh:mma)
     */
    @Override
    public String toDisplayString() {
        return "[E]" + super.toDisplayString() + " (from: " + DateTimeParser.formatForDisplay(from) 
                + " to: " + DateTimeParser.formatForDisplay(to) + ")";
    }

    /**
     * Returns the start date/time of this event.
     *
     * @return the start date/time as a LocalDateTime
     */
    public LocalDateTime getFromDateTime() {
        return from;
    }

    /**
     * Returns the end date/time of this event.
     *
     * @return the end date/time as a LocalDateTime
     */
    public LocalDateTime getToDateTime() {
        return to;
    }

    /**
     * Returns the start date/time as a string in storage format.
     * Used for saving to file.
     *
     * @return the start date/time as a string
     */
    public String getFrom() {
        return DateTimeParser.formatForStorage(from);
    }

    /**
     * Returns the end date/time as a string in storage format.
     * Used for saving to file.
     *
     * @return the end date/time as a string
     */
    public String getTo() {
        return DateTimeParser.formatForStorage(to);
    }
}

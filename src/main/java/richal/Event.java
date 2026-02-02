package richal;

/**
 * Represents an event task with a start and end time.
 * Stores the task description, start time (from) and end time (to).
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Creates an event task with the given description and time range.
     *
     * @param description the task description
     * @param from        the start date or time
     * @param to          the end date or time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of this event for display.
     * Format: [E][status] description (from: start to: end)
     */
    @Override
    public String toDisplayString() {
        return "[E]" + super.toDisplayString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the start date/time of this event.
     *
     * @return the start date or time
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end date/time of this event.
     *
     * @return the end date or time
     */
    public String getTo() {
        return to;
    }
}

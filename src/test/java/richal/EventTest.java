package richal;

import java.time.LocalDateTime;
import richal.task.Event;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Event class.
 */
public class EventTest {

    @Test
    public void constructor_withLocalDateTime_success() {
        LocalDateTime from = LocalDateTime.of(2019, 8, 6, 14, 0);
        LocalDateTime to = LocalDateTime.of(2019, 8, 6, 16, 0);
        Event event = new Event("project meeting", from, to);
        assertEquals("project meeting", event.getDescription());
        assertEquals(from, event.getFromDateTime());
        assertEquals(to, event.getToDateTime());
    }

    @Test
    public void constructor_withValidStrings_success() {
        Event event = new Event("meeting", "2019-08-06 1400", "2019-08-06 1600");
        assertEquals("meeting", event.getDescription());
        assertNotNull(event.getFromDateTime());
        assertNotNull(event.getToDateTime());
    }

    @Test
    public void constructor_withInvalidFromString_throwsException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Event("task", "invalid date", "2019-08-06 1600");
        });
    }

    @Test
    public void constructor_withInvalidToString_throwsException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Event("task", "2019-08-06 1400", "invalid date");
        });
    }

    @Test
    public void toDisplayString_notDone_correctFormat() {
        LocalDateTime from = LocalDateTime.of(2019, 8, 6, 14, 0);
        LocalDateTime to = LocalDateTime.of(2019, 8, 6, 16, 0);
        Event event = new Event("meeting", from, to);
        assertEquals("[E][ ]meeting (from: Aug 06 2019, 02:00pm to: Aug 06 2019, 04:00pm)", 
                     event.toDisplayString());
    }

    @Test
    public void toDisplayString_done_correctFormat() {
        LocalDateTime from = LocalDateTime.of(2019, 8, 6, 14, 0);
        LocalDateTime to = LocalDateTime.of(2019, 8, 6, 16, 0);
        Event event = new Event("meeting", from, to);
        event.markDone();
        assertEquals("[E][X]meeting (from: Aug 06 2019, 02:00pm to: Aug 06 2019, 04:00pm)", 
                     event.toDisplayString());
    }

    @Test
    public void getFrom_returnsStorageFormat() {
        LocalDateTime from = LocalDateTime.of(2019, 8, 6, 14, 0);
        LocalDateTime to = LocalDateTime.of(2019, 8, 6, 16, 0);
        Event event = new Event("meeting", from, to);
        assertEquals("2019-08-06 1400", event.getFrom());
    }

    @Test
    public void getTo_returnsStorageFormat() {
        LocalDateTime from = LocalDateTime.of(2019, 8, 6, 14, 0);
        LocalDateTime to = LocalDateTime.of(2019, 8, 6, 16, 0);
        Event event = new Event("meeting", from, to);
        assertEquals("2019-08-06 1600", event.getTo());
    }

    @Test
    public void getFromDateTime_returnsCorrectDateTime() {
        LocalDateTime from = LocalDateTime.of(2019, 8, 6, 14, 0);
        LocalDateTime to = LocalDateTime.of(2019, 8, 6, 16, 0);
        Event event = new Event("meeting", from, to);
        assertEquals(from, event.getFromDateTime());
    }

    @Test
    public void getToDateTime_returnsCorrectDateTime() {
        LocalDateTime from = LocalDateTime.of(2019, 8, 6, 14, 0);
        LocalDateTime to = LocalDateTime.of(2019, 8, 6, 16, 0);
        Event event = new Event("meeting", from, to);
        assertEquals(to, event.getToDateTime());
    }

    @Test
    public void constructor_variousDateFormats_success() {
        assertDoesNotThrow(() -> new Event("event1", "2019-08-06 1400", "2019-08-06 1600"));
        assertDoesNotThrow(() -> new Event("event2", "6/8/2019 1400", "6/8/2019 1600"));
        assertDoesNotThrow(() -> new Event("event3", "2019-08-06", "2019-08-07"));
    }
}

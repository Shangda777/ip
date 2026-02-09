package richal;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Deadline class.
 */
public class DeadlineTest {

    @Test
    public void constructor_withLocalDateTime_success() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        Deadline deadline = new Deadline("return book", dateTime);
        assertEquals("return book", deadline.getDescription());
        assertEquals(dateTime, deadline.getByDateTime());
    }

    @Test
    public void constructor_withValidString_success() {
        Deadline deadline = new Deadline("submit report", "2019-12-02 1800");
        assertEquals("submit report", deadline.getDescription());
        assertNotNull(deadline.getByDateTime());
    }

    @Test
    public void constructor_withInvalidString_throwsException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("task", "invalid date");
        });
    }

    @Test
    public void toDisplayString_notDone_correctFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        Deadline deadline = new Deadline("return book", dateTime);
        assertEquals("[D][ ]return book (by: Dec 02 2019, 06:00pm)", 
                     deadline.toDisplayString());
    }

    @Test
    public void toDisplayString_done_correctFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        Deadline deadline = new Deadline("return book", dateTime);
        deadline.markDone();
        assertEquals("[D][X]return book (by: Dec 02 2019, 06:00pm)", 
                     deadline.toDisplayString());
    }

    @Test
    public void getBy_returnsStorageFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        Deadline deadline = new Deadline("return book", dateTime);
        assertEquals("2019-12-02 1800", deadline.getBy());
    }

    @Test
    public void getByDateTime_returnsCorrectDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 10, 15, 14, 30);
        Deadline deadline = new Deadline("meeting", dateTime);
        assertEquals(dateTime, deadline.getByDateTime());
    }

    @Test
    public void constructor_variousDateFormats_success() {
        assertDoesNotThrow(() -> new Deadline("task1", "2019-12-02 1800"));
        assertDoesNotThrow(() -> new Deadline("task2", "2/12/2019 1800"));
        assertDoesNotThrow(() -> new Deadline("task3", "2019-12-02"));
    }

    @Test
    public void markDone_changesDisplayString() {
        Deadline deadline = new Deadline("submit", "2019-12-02 1800");
        deadline.markDone();
        assertTrue(deadline.toDisplayString().contains("[X]"));
    }
}

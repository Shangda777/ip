package richal;

import java.time.LocalDateTime;
import richal.util.DateTimeParser;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Tests for the DateTimeParser utility class.
 */
public class DateTimeParserTest {

    @Test
    public void parse_standardFormat_success() {
        LocalDateTime result = DateTimeParser.parse("2019-12-02 1800");
        assertEquals(2019, result.getYear());
        assertEquals(12, result.getMonthValue());
        assertEquals(2, result.getDayOfMonth());
        assertEquals(18, result.getHour());
        assertEquals(0, result.getMinute());
    }

    @Test
    public void parse_standardFormatWithColon_success() {
        LocalDateTime result = DateTimeParser.parse("2019-12-02 18:00");
        assertEquals(2019, result.getYear());
        assertEquals(12, result.getMonthValue());
        assertEquals(2, result.getDayOfMonth());
        assertEquals(18, result.getHour());
        assertEquals(0, result.getMinute());
    }

    @Test
    public void parse_slashFormat_success() {
        LocalDateTime result = DateTimeParser.parse("2/12/2019 1800");
        assertEquals(2019, result.getYear());
        assertEquals(12, result.getMonthValue());
        assertEquals(2, result.getDayOfMonth());
        assertEquals(18, result.getHour());
        assertEquals(0, result.getMinute());
    }

    @Test
    public void parse_dateOnly_defaultsToMidnight() {
        LocalDateTime result = DateTimeParser.parse("2019-12-02");
        assertEquals(2019, result.getYear());
        assertEquals(12, result.getMonthValue());
        assertEquals(2, result.getDayOfMonth());
        assertEquals(0, result.getHour());
        assertEquals(0, result.getMinute());
    }

    @Test
    public void parse_dateOnlySlashFormat_success() {
        LocalDateTime result = DateTimeParser.parse("2/12/2019");
        assertEquals(2019, result.getYear());
        assertEquals(12, result.getMonthValue());
        assertEquals(2, result.getDayOfMonth());
    }

    @Test
    public void parse_invalidFormat_throwsException() {
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeParser.parse("invalid date");
        });
    }

    @Test
    public void parse_emptyString_throwsException() {
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeParser.parse("");
        });
    }

    @Test
    public void parse_withLeadingTrailingSpaces_success() {
        LocalDateTime result = DateTimeParser.parse("  2019-12-02 1800  ");
        assertEquals(2019, result.getYear());
        assertEquals(12, result.getMonthValue());
        assertEquals(2, result.getDayOfMonth());
    }

    @Test
    public void formatForDisplay_correctFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        String result = DateTimeParser.formatForDisplay(dateTime);
        assertEquals("Dec 02 2019, 06:00pm", result);
    }

    @Test
    public void formatForDisplay_morning_correctFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 8, 6, 9, 30);
        String result = DateTimeParser.formatForDisplay(dateTime);
        assertEquals("Aug 06 2019, 09:30am", result);
    }

    @Test
    public void formatForDisplay_afternoon_correctFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 8, 6, 14, 0);
        String result = DateTimeParser.formatForDisplay(dateTime);
        assertEquals("Aug 06 2019, 02:00pm", result);
    }

    @Test
    public void formatForStorage_correctFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        String result = DateTimeParser.formatForStorage(dateTime);
        assertEquals("2019-12-02 1800", result);
    }

    @Test
    public void formatForStorage_singleDigitTime_correctFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 8, 6, 9, 5);
        String result = DateTimeParser.formatForStorage(dateTime);
        assertEquals("2019-08-06 0905", result);
    }

    @Test
    public void parse_thenFormat_roundTrip() {
        String original = "2019-12-02 1800";
        LocalDateTime parsed = DateTimeParser.parse(original);
        String formatted = DateTimeParser.formatForStorage(parsed);
        assertEquals(original, formatted);
    }

    @Test
    public void parse_variousFormats_allSucceed() {
        assertDoesNotThrow(() -> DateTimeParser.parse("2019-12-02 1800"));
        assertDoesNotThrow(() -> DateTimeParser.parse("2019-12-02 18:00"));
        assertDoesNotThrow(() -> DateTimeParser.parse("2/12/2019 1800"));
        assertDoesNotThrow(() -> DateTimeParser.parse("02/12/2019 18:00"));
        assertDoesNotThrow(() -> DateTimeParser.parse("2019-12-02"));
        assertDoesNotThrow(() -> DateTimeParser.parse("2/12/2019"));
    }
}

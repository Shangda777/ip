package richal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for parsing various date and time formats.
 */
public class DateTimeParser {
    
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = new ArrayList<>();
    private static final List<DateTimeFormatter> DATE_ONLY_FORMATTERS = new ArrayList<>();
    
    static {
        // Date and time formats
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("M/d/yyyy HH:mm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"));
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        
        // Date only formats (will use 00:00 as default time)
        DATE_ONLY_FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DATE_ONLY_FORMATTERS.add(DateTimeFormatter.ofPattern("d/M/yyyy"));
        DATE_ONLY_FORMATTERS.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DATE_ONLY_FORMATTERS.add(DateTimeFormatter.ofPattern("M/d/yyyy"));
        DATE_ONLY_FORMATTERS.add(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }
    
    /**
     * Parses a date/time string into a LocalDateTime object.
     * Tries multiple formats until one succeeds.
     *
     * @param dateTimeString the date/time string to parse
     * @return the parsed LocalDateTime
     * @throws DateTimeParseException if none of the formats match
     */
    public static LocalDateTime parse(String dateTimeString) throws DateTimeParseException {
        String trimmed = dateTimeString.trim();
        
        // Try date-time formats first
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }
        
        // Try date-only formats (default time to 00:00)
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            try {
                // Parse date only and set time to 00:00
                java.time.LocalDate date = java.time.LocalDate.parse(trimmed, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }
        
        throw new DateTimeParseException("Unable to parse date/time: " + dateTimeString, dateTimeString, 0);
    }
    
    /**
     * Formats a LocalDateTime for display.
     * Format: MMM dd yyyy, hh:mma (e.g., Dec 02 2019, 06:00PM)
     *
     * @param dateTime the LocalDateTime to format
     * @return the formatted string
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
        return dateTime.format(formatter);
    }
    
    /**
     * Formats a LocalDateTime for storage.
     * Format: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)
     *
     * @param dateTime the LocalDateTime to format
     * @return the formatted string
     */
    public static String formatForStorage(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTime.format(formatter);
    }
}

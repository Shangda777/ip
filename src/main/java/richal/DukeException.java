package richal;

/**
 * Represents an exception thrown when the user gives an invalid command.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException with the given message.
     *
     * @param message the error message
     */
    public DukeException(String message) {
        super(message);
    }
}

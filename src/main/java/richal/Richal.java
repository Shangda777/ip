package richal;

import java.util.List;

/**
 * Richal is a simple chatbot program.
 * It receives user input and manages tasks until the user types "bye" to exit.
 */
public class Richal {

    private static final String FILE_PATH = "./data/richal.txt";
    
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Richal chatbot with the specified file path for data storage.
     *
     * @param filePath the path to the data file
     */
    public Richal(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            List<Task> tasks = storage.load();
            taskList = new TaskList(tasks);
        } catch (DukeException e) {
            taskList = new TaskList(100);
        }
    }

    /**
     * Gets the welcome message.
     */
    public String getWelcome() {
        return ui.getWelcomeMessage();
    }

    /**
     * Processes user input and returns the response.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseAndGetResponse(input, taskList, ui, storage);
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.getErrorMessage("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Main entry point of the program.
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        new Richal(FILE_PATH);
    }
}

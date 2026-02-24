package richal;

import java.util.List;

import richal.ai.AiHelper;
import richal.command.Parser;
import richal.storage.Storage;
import richal.task.Task;
import richal.task.TaskList;
import richal.ui.Ui;

/**
 * Richal is a simple chatbot program.
 */
public class Richal {

    private static final String FILE_PATH = "./data/richal.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private AiHelper aiHelper;

    /**
     * Creates a Richal instance, loading existing tasks from the given file.
     *
     * @param filePath path to the data file
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
        aiHelper = initAiHelper();
    }

    private AiHelper initAiHelper() {
        try {
            return new AiHelper();
        } catch (Exception e) {
            return null;
        }
    }

    /** Returns the welcome message to display on startup. */
    public String getWelcome() {
        return ui.getWelcomeMessage();
    }

    /**
     * Processes a user command and returns the response.
     *
     * @param input the raw user input
     * @return the response string
     */
    public String getResponse(String input) {
        try {
            return Parser.parseAndGetResponse(input, taskList, ui, storage, aiHelper);
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.getErrorMessage("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Richal(FILE_PATH);
    }
}

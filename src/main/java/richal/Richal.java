package richal;

import java.util.List;

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

    public String getWelcome() {
        return ui.getWelcomeMessage();
    }

    public String getResponse(String input) {
        try {
            return Parser.parseAndGetResponse(input, taskList, ui, storage);
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

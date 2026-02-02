package richal;

import java.util.List;

/**
 * Richal is a simple chatbot program.
 * It receives user input and manages tasks until the user types "bye" to exit.
 */
public class Richal {

    private static final String FILE_PATH = "./data/duke.txt";
    
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
            ui.showTasksLoaded(taskList.getSize());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList(100);
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcome();
        
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                isExit = Parser.parse(input, taskList, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        
        ui.close();
    }

    /**
     * Main entry point of the program.
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        new Richal(FILE_PATH).run();
    }
}

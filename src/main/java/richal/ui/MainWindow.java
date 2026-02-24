package richal.ui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import richal.Richal;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Richal richal;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUsers.png"));
    private Image richalImage = new Image(this.getClass().getResourceAsStream("/images/DaRachel.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRichal(Richal r) {
        assert r != null : "Injected Richal instance should not be null";
        richal = r;
        dialogContainer.getChildren().add(
            DialogBox.getRichalDialog(richal.getWelcome(), richalImage)
        );
    }

    @FXML
    private void handleUserInput() {
        assert richal != null : "Richal instance should be initialized before handling input";
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        // Show user message immediately and disable input while processing
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();
        userInput.setDisable(true);
        sendButton.setDisable(true);

        // Run Richal's response on a background thread to avoid blocking the UI
        Task<String> task = new Task<>() {
            @Override
            protected String call() {
                return richal.getResponse(input);
            }
        };

        task.setOnSucceeded(event -> {
            String response = task.getValue();
            boolean isError = response.startsWith("OOPS!!!");
            dialogContainer.getChildren().add(
                isError ? DialogBox.getRichalErrorDialog(response, richalImage)
                        : DialogBox.getRichalDialog(response, richalImage)
            );
            userInput.setDisable(false);
            sendButton.setDisable(false);
            userInput.requestFocus();

            if (input.equalsIgnoreCase("bye")) {
                Platform.exit();
            }
        });

        task.setOnFailed(event -> {
            dialogContainer.getChildren().add(
                DialogBox.getRichalErrorDialog("Something went wrong. Please try again.", richalImage)
            );
            userInput.setDisable(false);
            sendButton.setDisable(false);
            userInput.requestFocus();
        });

        new Thread(task).start();
    }
}

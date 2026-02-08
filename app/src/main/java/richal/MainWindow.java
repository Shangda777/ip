package richal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    /**
     * Injects the Richal instance.
     */
    public void setRichal(Richal r) {
        richal = r;
        // Show welcome message
        dialogContainer.getChildren().add(
            DialogBox.getRichalDialog(richal.getWelcome(), richalImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Richal's reply,
     * and appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = richal.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getRichalDialog(response, richalImage)
        );
        userInput.clear();
        
        // Close the application when user types "bye"
        if (input.trim().equalsIgnoreCase("bye")) {
            javafx.application.Platform.exit();
        }
    }
}

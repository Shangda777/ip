package richal.ui;

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
        String input = userInput.getText();
        String response = richal.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getRichalDialog(response, richalImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            javafx.application.Platform.exit();
        }
    }
}

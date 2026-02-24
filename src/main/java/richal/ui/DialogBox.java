package richal.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * A dialog box with an ImageView and a Label.
 * A-BetterGui: asymmetric conversation (user vs Richal), error highlighting, profile circle clip.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser, boolean isError) {
        assert text != null : "Dialog text should not be null";
        assert img != null : "Dialog image should not be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load DialogBox FXML", e);
        }

        dialog.setText(text);
        dialog.getStyleClass().add("dialog");
        displayPicture.setImage(img);

        getStyleClass().add("dialog-box");
        if (isUser) {
            getStyleClass().add("user");
        } else {
            getStyleClass().add("richal");
            if (isError) {
                getStyleClass().add("error");
            }
        }

        double size = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        Circle clip = new Circle(size / 2, size / 2, size / 2);
        displayPicture.setClip(clip);

        if (!isUser) {
            flip();
        }
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user's message (image on the right).
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true, false);
    }

    /**
     * Creates a dialog box for Richal's reply (image on the left).
     */
    public static DialogBox getRichalDialog(String text, Image img) {
        return new DialogBox(text, img, false, false);
    }

    /**
     * Creates a dialog box for Richal's error message (highlighted style).
     */
    public static DialogBox getRichalErrorDialog(String text, Image img) {
        return new DialogBox(text, img, false, true);
    }
}

package richal;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import richal.ui.MainWindow;

/**
 * A GUI for Richal using FXML.
 */
public class Main extends Application {

    private Richal richal = new Richal("./data/richal.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(Main.class.getResource("/view/style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Richal");
            stage.setResizable(true);
            fxmlLoader.<MainWindow>getController().setRichal(richal);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TypingGameApplication extends Application {

    private static final Logger LOGGER = Logger.getLogger(TypingGameApplication.class.getName());
    @Override
    public void start(Stage primaryStage) {
        Scene scene = createScene();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("images/logo.png"));

        primaryStage.show();
    }

    private Scene createScene() {
        Pane root = loadFXML("/fxml/MainMenu.fxml");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/style.css");

        return scene;
    }

    private Pane loadFXML(String fxmlPath) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlPath));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Failed to load " + fxmlPath, e);
            return null;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
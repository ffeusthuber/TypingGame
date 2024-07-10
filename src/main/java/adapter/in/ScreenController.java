package adapter.in;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ScreenController {
    private final Scene main;
    private static ScreenController instance;


    private ScreenController(Scene main) {
        this.main = main;
        instance = this;
    }

    static void setInstance(ScreenController instance) {
        ScreenController.instance = instance;
    }

    public static ScreenController getInstance(Scene scene) {
        if(instance == null) {
            return new ScreenController(scene);
        }
        return instance;
    }

    void activateTypingGame() {
        activate("/TypingGame.fxml");
    }

    void activateGameOver() {
        activate("/GameOverMenu.fxml");
    }

    private void activate(String fxmlPath) {
        try {
            main.setRoot(FXMLLoader.load(getClass().getResource(fxmlPath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load screen: " + fxmlPath, e);
        }
    }
}

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
        activateScene("/fxml/TypingGame.fxml", null);
    }

    void activateHowToPlay() {
        activateScene("/fxml/HowToPlay.fxml", null);
    }

    void activateGameOver() {
        activateScene("/fxml/GameOverMenu.fxml", null);
    }

    void activateGameOver(String time) {
        activateScene("/fxml/GameOverMenu.fxml", time);
    }

    public void activateMainMenu() {
        activateScene("/fxml/MainMenu.fxml", null);
    }

    private void activateScene(String fxmlPath, String parameter) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            main.setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load screen: " + fxmlPath, e);
        }
        if (parameter != null) {
            GameOverMenuController controller = loader.getController();
            controller.initData(parameter);
        }
    }
}

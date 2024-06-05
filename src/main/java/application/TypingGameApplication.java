package application;

import domain.TypingGame;
import domain.Word;
import domain.port.out.DisplayPort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class TypingGameApplication extends Application implements DisplayPort {
    // TODO: implements KeyPressListener
    // TODO: has KeyPressHandler
    private Label l;

    @Override
    public void start(Stage stage) {
        TypingGame typingGame = new TypingGame(this);
        typingGame.start();
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void display(List<Word> words) {
        l.setText(words.get(0).getRemainingWord());
    }

    public static void main(String[] args) {
        launch();
    }
}
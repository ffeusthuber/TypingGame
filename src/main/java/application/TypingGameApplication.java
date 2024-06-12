package application;

import domain.TypingGame;
import domain.Word;
import domain.port.in.KeyPressListener;
import domain.port.out.DisplayPort;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class TypingGameApplication extends Application implements DisplayPort {
    KeyPressListener keyPressListener;
    private Pane root;

    @Override
    public void start(Stage stage) {
        TypingGame typingGame = new TypingGame(this);
        keyPressListener = typingGame.getKeyPressListener();
        typingGame.start();


        Label initialLabel = new Label("Press any key");
        root = new Pane(initialLabel);
        Scene scene = new Scene(root, 640, 480);
        scene.setOnKeyPressed(this::handleKeyPressed);

        stage.setScene(scene);
        stage.show();
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        String key;
        if (keyEvent.getCode().isLetterKey()) {
            key = keyEvent.isShiftDown() ? keyEvent.getText().toUpperCase() : keyEvent.getText().toLowerCase();
            keyPressListener.onKeyPressed(key);
        }
    }

    @Override
    public void display(List<Word> words) {
        Platform.runLater(() -> {
            root.getChildren().clear();

            for (Word word : words) {
                Label wordLabel = new Label(word.getRemainingWord());
                wordLabel.setLayoutX(word.getPosition().x());
                wordLabel.setLayoutY(word.getPosition().y());
                root.getChildren().add(wordLabel);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
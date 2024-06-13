package application;

import adapter.out.TextFileWordRepository;
import domain.TypingGame;
import domain.Word;
import domain.port.in.KeyPressListener;
import domain.port.out.DisplayPort;
import domain.port.out.WordRepository;
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
        DisplayPort display = this;
        WordRepository wordRepository = new TextFileWordRepository("src/main/java/config/wordList.txt");
        TypingGame typingGame = new TypingGame(3,display,wordRepository);
        keyPressListener = typingGame.getKeyPressListener();
        typingGame.start();

        root = new Pane();
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
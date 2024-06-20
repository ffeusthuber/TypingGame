package adapter.out;

import domain.TypingGame;
import domain.Word;
import domain.port.in.KeyPressListener;
import domain.port.out.DisplayPort;
import domain.port.out.WordRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.List;

public class TypingGameController implements DisplayPort {
    private KeyPressListener keyPressListener;
    TypingGame typingGame;

    @FXML
    Pane gameFieldView;

    @FXML
    public void initialize() {
        startTypingGame();
    }

    private void startTypingGame() {
        WordRepository wordRepository = new TextFileWordRepository("src/main/java/config/wordList.txt");
        typingGame = new TypingGame(3, this, wordRepository);
        keyPressListener = typingGame.getKeyPressListener();
        typingGame.start();
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().isLetterKey()) {
            String key = keyEvent.getText().toLowerCase();
            if(keyEvent.isShiftDown()) key = key.toUpperCase();
            keyPressListener.onKeyPressed(key);
        }
    }

    @Override
    public void display(List<Word> words) {
        Platform.runLater(() -> {
            gameFieldView.getChildren().clear();
            for (Word word : words) {
                Label wordLabel = new Label(word.getRemainingWord());
                wordLabel.setLayoutX(word.getPosition().x());
                wordLabel.setLayoutY(word.getPosition().y());
                gameFieldView.getChildren().add(wordLabel);
            }
        });
    }

    public void setKeyPressListener(KeyPressListener keyPressListener) {
        this.keyPressListener = keyPressListener;
    }
}

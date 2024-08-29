package adapter.in;

import domain.TypingGame;
import domain.Word;
import domain.WordTargeter;
import domain.port.in.KeyPressListener;
import domain.port.out.DisplayPort;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class TypingGameController implements DisplayPort {
    KeyPressListener keyPressListener;
    TypingGame typingGame;

    @FXML
    Pane gameFieldView;

    @FXML
    public void initialize() {
        initializeGameFieldView();
        startTypingGame();
    }

    private void initializeGameFieldView() {
        gameFieldView.setFocusTraversable(true);
        gameFieldView.requestFocus();
    }

    public void startTypingGame() {
        typingGame = new TypingGame(this);
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
            List<Word> wordsToDisplay = new ArrayList<>(words);
            for (Word word : wordsToDisplay) {
                Label wordLabel = createWordLabel(word);
                changeStyleOfTargetedWord(wordLabel, word);
                gameFieldView.getChildren().add(wordLabel);
            }
        });
    }

    private Label createWordLabel(Word word) {
        Label wordLabel = new Label(word.getRemainingWord());
        wordLabel.setLayoutX(word.getPosition().x());
        wordLabel.setLayoutY(word.getPosition().y());
        return wordLabel;
    }

    void changeStyleOfTargetedWord(Label wordLabel, Word word) {
        WordTargeter wordTargeter = typingGame.getWordTargeter();
        wordLabel.getStyleClass().removeAll("targeted-word");
        if (wordTargeter != null && wordTargeter.hasTarget() && wordTargeter.getTarget().equals(word)) {
            wordLabel.getStyleClass().add("targeted-word");
        }
    }

    @Override
    public void gameOver() {
        Scene scene = gameFieldView.getScene();
        ScreenController.getInstance(scene).activateGameOver();
    }
}

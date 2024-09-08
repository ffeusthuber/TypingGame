package adapter.in;

import domain.Position;
import domain.TypingGame;
import domain.Word;
import domain.WordTargeter;
import domain.port.in.KeyPressListener;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TypingGameControllerTest {
    private TypingGameController typingGameController;
    private WordTargeter mockWordTargeter;

    @BeforeAll
    public static void initJFX() {
        new JFXPanel();
    }

    @BeforeEach
    void setUp() {
        typingGameController = new TypingGameController();
        typingGameController.gameFieldView = mock(Pane.class);
        typingGameController.typingGame = mock(TypingGame.class);
        mockWordTargeter = mock(WordTargeter.class);
    }

    @Test
    void keyPressGetsPassedToKeyPressListener() {
        KeyEvent keyEvent = mock(KeyEvent.class);
        when(keyEvent.getCode()).thenReturn(KeyCode.A);
        when(keyEvent.getText()).thenReturn("a");
        KeyPressListener keyPressListener = mock(KeyPressListener.class);
        typingGameController.keyPressListener = keyPressListener;

        typingGameController.handleKeyPressed(keyEvent);

        verify(keyPressListener, times(1)).onKeyPressed("a");
    }

    @Test
    public void displayedWordsGetAreAddedToGameFieldAsLabels() throws InterruptedException {
        Word word1 = new Word("word1", new Position(10, 10));
        Word word2 = new Word("word2", new Position(20, 20));
        Pane gameFieldView = new Pane();
        typingGameController.gameFieldView = gameFieldView;

        typingGameController.display(Arrays.asList(word1, word2));
        Thread.sleep(500);

        assertThat(gameFieldView.getChildren()).hasSize(2);
        assertThat(gameFieldView.getChildren().get(0)).isInstanceOf(Label.class);
    }

    @Test
    void screenControllerActivatesGameOverScreenOnGameOverAndHandsOverStopwatchTime() {
        ScreenController mockScreenController = mock(ScreenController.class);
        ScreenController.setInstance(mockScreenController);
        Text stopwatchView = new Text();
        typingGameController.stopwatchView = stopwatchView;

        typingGameController.gameOver();

        verify(mockScreenController).activateGameOver(stopwatchView.getText());
    }

    @Test
    void styleOfTargetedWordGetsChanged() {
        Word word = new Word("target", new Position(10, 10));
        Label wordLabel = new Label(word.getRemainingWord());

        when(typingGameController.typingGame.getWordTargeter()).thenReturn(mockWordTargeter);
        when(mockWordTargeter.hasTarget()).thenReturn(true);
        when(mockWordTargeter.getTarget()).thenReturn(word);

        typingGameController.styleTargetedWord(wordLabel, word);

        assertThat(wordLabel.getStyleClass()).contains("targeted-word");
    }

    @Test
    void styleOfNotTargetedWordDoesNotGetChanged() {
        Word word = new Word("notTarget", new Position(10, 10));
        Label wordLabel = new Label(word.getRemainingWord());

        when(typingGameController.typingGame.getWordTargeter()).thenReturn(mockWordTargeter);
        when(mockWordTargeter.hasTarget()).thenReturn(true);
        when(mockWordTargeter.getTarget()).thenReturn(new Word("otherWord", new Position(0, 0)));

        typingGameController.styleTargetedWord(wordLabel, word);

        assertThat(wordLabel.getStyleClass()).doesNotContain("targeted-word");
    }

    @Test
    void remainingLivesAreDisplayed() throws InterruptedException {
        Pane remainingLivesView = new Pane();
        typingGameController.remainingLivesView = remainingLivesView;
        when(typingGameController.typingGame.getPlayerLives()).thenReturn(3);

        typingGameController.updateLives();
        Thread.sleep(500);

        assertThat(remainingLivesView.getChildren()).hasSize(3);
    }

    @Test
    void stopwatchTimeIsDisplayedInCorrectFormat() throws InterruptedException {
        Text stopwatchView = new Text();
        typingGameController.stopwatchView = stopwatchView;
        TemporalAmount time = Duration.ofSeconds(10);

        typingGameController.display(time);
        Thread.sleep(500);

        assertThat(stopwatchView.getText()).isEqualTo("00:10:00");
    }

}
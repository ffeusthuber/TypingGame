package adapter.in;

import domain.Position;
import domain.TypingGame;
import domain.Word;
import domain.port.in.KeyPressListener;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TypingGameControllerTest {
    private TypingGameController typingGameController;

    @BeforeAll
    public static void initJFX() {
        new JFXPanel();
    }
    @BeforeEach
    public void setUp() {
        typingGameController = new TypingGameController();
        Pane mockGameFieldView = mock(Pane.class);
        typingGameController.gameFieldView = mockGameFieldView;
        TypingGame typingGame = mock(TypingGame.class);
        typingGameController.typingGame = typingGame;
    }

    @Test
    public void keyPressGetsPassedToKeyPressListener() {
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
    void screenControllerActivatesGameOverScreenOnGameOver() {
        ScreenController mockScreenController = mock(ScreenController.class);
        ScreenController.setInstance(mockScreenController);

        typingGameController.gameOver();

        verify(mockScreenController).activateGameOver();
    }
}
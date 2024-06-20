package adapter.out;

import domain.Position;
import domain.TypingGame;
import domain.Word;
import domain.port.in.KeyPressListener;
import javafx.embed.swing.JFXPanel;
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
    private TypingGameController controller;

    @BeforeAll
    public static void initJFX() {
        new JFXPanel();
    }
    @BeforeEach
    public void setUp() {
        controller = new TypingGameController();
        TypingGame typingGame = mock(TypingGame.class);
        controller.typingGame = typingGame;
    }

    @Test
    public void keyPressGetsPassedToKeyPressListener() {
        KeyEvent keyEvent = mock(KeyEvent.class);
        when(keyEvent.getCode()).thenReturn(KeyCode.A);
        when(keyEvent.getText()).thenReturn("a");

        KeyPressListener keyPressListener = mock(KeyPressListener.class);
        controller.setKeyPressListener(keyPressListener);

        controller.handleKeyPressed(keyEvent);

        verify(keyPressListener, times(1)).onKeyPressed("a");
    }

    @Test
    public void displayedWordsGetAreAddedAsGameFieldChildren() throws InterruptedException {
        Word word1 = new Word("word1", new Position(10, 10));
        Word word2 = new Word("word2", new Position(20, 20));
        controller.gameFieldView = new Pane();

        controller.display(Arrays.asList(word1, word2));
        Thread.sleep(75);

        assertThat(controller.gameFieldView.getChildren()).hasSize(2);
    }
}
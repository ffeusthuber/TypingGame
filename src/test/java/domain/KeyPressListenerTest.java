package domain;

import domain.port.in.KeyPressListener;
import domain.port.in.KeyPressListenerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class KeyPressListenerTest {
    private GameField gameField;
    private WordTargeter wordTargeter;
    private KeyPressListener keyPressListener;

    @BeforeEach
    void setUp() {
        gameField = new GameField();
        wordTargeter = new WordTargeter();
        keyPressListener = new KeyPressListenerImpl(gameField, wordTargeter);
    }

    @Test
    void ifWordTargeterHasNoTargetTargetIsSetOnCorrectKeyPressed() {
        Word word = new Word("Apple");
        gameField.addWord(word);

        keyPressListener.onKeyPressed("A");

        assertThat(wordTargeter.getTarget()).isEqualTo(word);
    }

    @Test
    void whenCorrectKeyIsPressedCharIsRemovedFromRemainingTargetedWord() {
        Word word = new Word("Apple");
        gameField.addWord(word);

        keyPressListener.onKeyPressed("A");

        assertThat(word.getRemainingWord()).isEqualTo("pple");
    }

    @Test
    void whenAllKeysOfWordArePressedWordTargeterDropsTargetAndGameFieldRemovesWord() {
        Word word = new Word("abc");
        gameField.addWord(word);

        keyPressListener.onKeyPressed("a");
        keyPressListener.onKeyPressed("b");
        keyPressListener.onKeyPressed("c");

        assertThat(gameField.getWords()).isEqualTo(Collections.EMPTY_LIST);
        assertThat(wordTargeter.hasTarget()).isFalse();
    }
}

package domain;

import domain.port.in.KeyPressListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KeyPressHandlerTest {
    private GameField gameField;
    private WordTargeter wordTargeter;
    private KeyPressListener keyPressHandler;

    @BeforeEach
    void setUp() {
        int gameFieldHeight = 100;
        List<Position> spawnPoints = List.of(new Position(0,0));
        gameField = new GameField(gameFieldHeight, spawnPoints);
        wordTargeter = new WordTargeter();
        keyPressHandler = new KeyPressHandler(gameField, wordTargeter);
    }

    @Test
    void ifWordTargeterHasNoTargetTargetIsSetOnCorrectKeyPressed() {
        Word word = new Word("Apple");
        gameField.addWord(word);

        assertThat(wordTargeter.hasTarget()).isFalse();
        keyPressHandler.onKeyPressed("A");

        assertThat(wordTargeter.getTarget()).isEqualTo(word);
    }

    @Test
    void whenCorrectKeyIsPressedCharIsRemovedFromRemainingTargetedWord() {
        Word word = new Word("Apple");
        gameField.addWord(word);

        keyPressHandler.onKeyPressed("A");

        assertThat(word.getRemainingWord()).isEqualTo("pple");
    }

    @Test
    void whenAllKeysOfWordArePressedWordTargeterDropsTargetAndGameFieldRemovesWord() {
        Word word = new Word("abc");
        gameField.addWord(word);

        keyPressHandler.onKeyPressed("a");
        keyPressHandler.onKeyPressed("b");
        keyPressHandler.onKeyPressed("c");

        assertThat(gameField.getWords()).isEqualTo(Collections.EMPTY_LIST);
        assertThat(wordTargeter.hasTarget()).isFalse();
    }
}

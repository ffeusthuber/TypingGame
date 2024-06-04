package domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameFieldTest {

    @Test
    void wordsCanBeAddedToGameField() {
        Word word = new Word("Apple");
        GameField gameField = new GameField();

        gameField.addWord(word);

        assertThat(gameField.getWords()).isEqualTo(List.of(word));
    }

    @Test
    void wordsCanBeRemovedFromGameField() {
        Word word = new Word("Apple");
        GameField gameField = new GameField();
        gameField.addWord(word);

        gameField.removeWord(word);

        assertThat(gameField.getWords()).isEqualTo(Collections.EMPTY_LIST);
    }
}

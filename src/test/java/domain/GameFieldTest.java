package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameFieldTest {

    private GameField gameField;

    @BeforeEach
    void setUpGameField() {
        int gameFieldHeight = 100;
        List<Position> spawnPoints = List.of(new Position(0,0));
        gameField = new GameField(gameFieldHeight,spawnPoints);
    }

    @Test
    void whenInstantiatedGameFieldIsEmpty() {
        assertThat(gameField.getWords()).isEmpty();
    }

    @Test
    void wordsCanBeAddedToGameField() {
        Word word = new Word("Apple");

        gameField.addWord(word);

        assertThat(gameField.getWords()).isEqualTo(List.of(word));
    }

    @Test
    void wordsCanBeRemovedFromGameField() {
        Word word = new Word("Apple");
        gameField.addWord(word);

        gameField.removeWord(word);

        assertThat(gameField.getWords()).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    void wordsAreMovedByTheCorrectStepSize() {
        Word word = new Word("Apple",new Position(0,0));
        gameField.addWord(word);
        int stepSize = 10;

        gameField.moveWords(stepSize);

        Word expected = new Word("Apple", new Position(0, stepSize));
        assertThat(gameField.getWords().get(0)).isEqualTo(expected);
    }
}

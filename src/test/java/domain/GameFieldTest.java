package domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameFieldTest {

    @Test
    void whenInstantiatedGameFieldIsEmpty() {
        GameField gameField = new GameField();

        assertThat(gameField.getWords()).isEmpty();
        assertThat(gameField.getSpawnPoints()).isEmpty();
    }

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

    @Test
    void spawnPointsCanBeAddedToGameField() {
        Position position = new Position(0,0);
        GameField gameField = new GameField();

        gameField.addSpawnPoint(position);

        assertThat(gameField.getSpawnPoints()).isEqualTo(List.of(position));
    }

    @Test
    void wordsAreMovedByTheCorrectStepSize() {
        Word word = new Word("Apple", new Position(0, 0));
        GameField gameField = new GameField();
        gameField.addWord(word);

        int stepSize = 10;
        gameField.moveWords(stepSize);
        Word expected = new Word("Apple", new Position(0, stepSize));

        assertThat(word).isEqualTo(expected);
    }
}

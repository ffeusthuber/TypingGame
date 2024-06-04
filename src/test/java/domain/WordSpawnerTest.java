package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSpawnerTest {

    @Test
    void whenSpawnIsCalledWordIsSpawnedOnRandomSpawnPoint() {
        GameField gameField = new GameField();
        StringSelector stringSelector = new StringSelectorStub("Banana");
        Position spawnPoint = new Position(1,1);
        SpawnPointSelector spawnPointSelector = new SpawnPointSelectorStub(spawnPoint);
        WordSpawner wordSpawner = new WordSpawner(stringSelector, spawnPointSelector,gameField);
        Word expected = new Word("Banana", spawnPoint);

        Word actual = wordSpawner.spawnOnRandomSpawnPoint();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void spawnedWordsGetAddedToGameField() {
        GameField gameField = new GameField();
        StringSelector stringSelector = new StringSelectorStub("Banana");
        Position spawnPoint = new Position(1,1);
        SpawnPointSelector spawnPointSelector = new SpawnPointSelectorStub(spawnPoint);
        WordSpawner wordSpawner = new WordSpawner(stringSelector, spawnPointSelector,gameField);
        Word word = new Word("Banana", spawnPoint);

        wordSpawner.spawnOnRandomSpawnPoint();

        assertThat(gameField.getWords()).isEqualTo(List.of(word));
    }

    @Test
    void wordSpawnerGetsBuiltWithSpawnPointsFromGameField() {
        GameField gameField = new GameField();
        Position spawnPoint = new Position(0,0);
        gameField.addSpawnPoint(spawnPoint);

        WordSpawner wordSpawner = WordSpawner.build(gameField);
        SpawnPointSelectorImpl spawnPointSelector = (SpawnPointSelectorImpl) wordSpawner.spawnPointSelector;

        assertThat(spawnPointSelector.getSpawnPoints()).isEqualTo(List.of(spawnPoint));
    }
}

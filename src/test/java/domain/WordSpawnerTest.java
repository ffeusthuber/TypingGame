package domain;

import adapter.out.WordRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSpawnerTest {

    private GameField gameField;
    private StringSelector stringSelector;
    private SpawnPointSelector spawnPointSelector;
    private Position spawnPoint;

    @BeforeEach
    void setUp() {
        int gameFieldHeight = 100;
        spawnPoint = new Position(1, 1);
        List<Position> spawnPoints = List.of(spawnPoint);
        gameField = new GameField(gameFieldHeight,spawnPoints);
        stringSelector = new StringSelectorStub("Banana");
        spawnPoint = new Position(1, 1);
        spawnPointSelector = new SpawnPointSelectorStub(spawnPoint);
    }

    @Test
    void whenSpawnIsCalledWordIsSpawnedOnRandomSpawnPoint() {
        WordSpawner wordSpawner = new WordSpawner(stringSelector, spawnPointSelector,gameField);
        Word expected = new Word("Banana", spawnPoint);

        Word actual = wordSpawner.spawnOnRandomSpawnPoint();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void spawnedWordsGetAddedToGameField() {
        WordSpawner wordSpawner = new WordSpawner(stringSelector, spawnPointSelector,gameField);
        Word word = new Word("Banana", spawnPoint);

        wordSpawner.spawnOnRandomSpawnPoint();

        assertThat(gameField.getWords()).isEqualTo(List.of(word));
    }

    @Test
    void wordSpawnerGetsBuiltWithSpawnPointsFromGameField() {
        WordSpawner wordSpawner = new WordSpawner(gameField, new WordRepositoryStub());
        SpawnPointSelectorImpl spawnPointSelector = (SpawnPointSelectorImpl) wordSpawner.getSpawnPointSelector();

        assertThat(spawnPointSelector.getSpawnPoints()).isEqualTo(List.of(spawnPoint));
    }
}

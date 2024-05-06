package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSpawnerTest {

    @Test
    void whenSpawnIsCalledWordIsSpawnedOnRandomSpawnPoint(){
        StringSelector stringSelector = new StringSelectorStub("Banana");
        Position spawnPoint = new Position(1,1);
        SpawnPointSelector spawnPointSelector = new SpawnPointSelectorStub(spawnPoint);
        WordSpawner wordSpawner = new WordSpawner(stringSelector, spawnPointSelector);
        Word expected = new Word("Banana", spawnPoint);

        Word actual = wordSpawner.spawnOnRandomSpawnPoint();

        assertThat(actual).isEqualTo(expected);
    }
}

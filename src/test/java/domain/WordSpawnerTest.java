package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSpawnerTest {

    @Test
    void whenSpawnIsCalledWordIsSpawnedOnSpawnPoint(){
        StringSelector stringSelector = new StringSelectorStub("Banana");
        Position spawnPoint = new Position(1,2);
        WordSpawner wordSpawner = new WordSpawner(stringSelector, spawnPoint);
        Word expected = new Word("Banana", spawnPoint);

        Word actual = wordSpawner.spawn();

        assertThat(actual).isEqualTo(expected);
    }
}

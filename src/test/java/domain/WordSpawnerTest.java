package domain;

import org.junit.jupiter.api.Test;
import util.RandomNumbers;
import util.RandomNumbersStub;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSpawnerTest {

    @Test
    void whenSpawnIsCalledWordIsSpawnedOnRandomSpawnPoint(){
        StringSelector stringSelector = new StringSelectorStub("Banana");
        RandomNumbers randomNumbers = new RandomNumbersStub(1);
        Position spawnPoint1 = new Position(1,1);
        Position spawnPoint2 = new Position(2,2);
        WordSpawner wordSpawner = new WordSpawner(stringSelector, randomNumbers, spawnPoint1,spawnPoint2);
        Word expected = new Word("Banana", spawnPoint2);

        Word actual = wordSpawner.spawn();

        assertThat(actual).isEqualTo(expected);
    }
}

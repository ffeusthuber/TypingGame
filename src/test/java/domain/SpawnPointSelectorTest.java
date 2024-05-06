package domain;

import org.junit.jupiter.api.Test;
import util.RandomNumbers;
import util.RandomNumbersStub;

import static org.assertj.core.api.Assertions.assertThat;

public class SpawnPointSelectorTest {

    @Test
    void randomSpawnPointGetsReturned(){
        RandomNumbers randomNumbersStub = new RandomNumbersStub(1);
        Position spawnPoint1 = new Position(1,1);
        Position spawnPoint2 = new Position(2,2);
        SpawnPointSelector spawnPointSelector = new SpawnPointSelectorImpl(randomNumbersStub, spawnPoint1, spawnPoint2);
        Position expected = spawnPoint2;

        Position actual = spawnPointSelector.random();

        assertThat(actual).isEqualTo(expected);
    }
}

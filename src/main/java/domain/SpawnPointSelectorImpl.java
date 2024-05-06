package domain;

import util.RandomNumbers;

public class SpawnPointSelectorImpl implements SpawnPointSelector {
    private final RandomNumbers randomNumberGenerator;
    private final Position[] spawnPoints;

    public SpawnPointSelectorImpl(RandomNumbers randomNumberGenerator, Position... spawnPoints) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.spawnPoints = spawnPoints;
    }

    @Override
    public Position random() {
        int chosenSpawnPointIndex = randomNumberGenerator.nextInt(spawnPoints.length-1);
        return spawnPoints[chosenSpawnPointIndex];
    }
}

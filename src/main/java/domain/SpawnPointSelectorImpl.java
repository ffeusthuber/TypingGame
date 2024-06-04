package domain;

import util.RandomNumbers;

import java.util.List;

public class SpawnPointSelectorImpl implements SpawnPointSelector {
    private final RandomNumbers randomNumberGenerator;
    private final List<Position> spawnPoints;

    public SpawnPointSelectorImpl(RandomNumbers randomNumberGenerator, List<Position> spawnPoints) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.spawnPoints = spawnPoints;
    }

    @Override
    public Position random() {
        int chosenSpawnPointIndex = randomNumberGenerator.nextInt(spawnPoints.size());
        return spawnPoints.get(chosenSpawnPointIndex);
    }

    public List<Position> getSpawnPoints() {
        return spawnPoints;
    }
}

package domain;

import util.RandomNumbers;

public class WordSpawner {
    private final StringSelector stringSelector;
    private final Position[] spawnPoints;
    private final RandomNumbers randomNumbers;

    public WordSpawner(StringSelector stringSelector, RandomNumbers randomNumbers, Position... spawnPoints) {
        this.stringSelector = stringSelector;
        this.randomNumbers = randomNumbers;
        this.spawnPoints = spawnPoints;
    }

    public Word spawn() {
        int chosenSpawnPoint = randomNumbers.nextInt(spawnPoints.length-1);
        return new Word(stringSelector.random(),spawnPoints[chosenSpawnPoint]);
    }
}

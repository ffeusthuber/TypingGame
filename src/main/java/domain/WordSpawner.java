package domain;

public class WordSpawner {
    private final StringSelector stringSelector;
    private final SpawnPointSelector spawnPointSelector;


    public WordSpawner(StringSelector stringSelector, SpawnPointSelector spawnPointSelector) {
        this.stringSelector = stringSelector;
        this.spawnPointSelector = spawnPointSelector;
    }

    public Word spawnOnRandomSpawnPoint() {
        return new Word(stringSelector.random(),spawnPointSelector.random());
    }
}

package domain;

public class WordSpawner {
    private final StringSelector stringSelector;
    private final Position spawnPoint;

    public WordSpawner(StringSelector stringSelector, Position spawnPoint) {
        this.stringSelector = stringSelector;
        this.spawnPoint = spawnPoint;
    }

    public Word spawn() {
        return new Word(stringSelector.random(),spawnPoint);
    }
}

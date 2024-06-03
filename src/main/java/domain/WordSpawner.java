package domain;

import adapter.out.TextFileWordRepository;
import domain.port.out.WordRepository;
import util.RandomNumberGenerator;
import util.RandomNumbers;

public class WordSpawner implements Runnable{
    private final StringSelector stringSelector;
    private final SpawnPointSelector spawnPointSelector;


    public WordSpawner(StringSelector stringSelector, SpawnPointSelector spawnPointSelector) {
        this.stringSelector = stringSelector;
        this.spawnPointSelector = spawnPointSelector;
    }

    public Word spawnOnRandomSpawnPoint() {
        return new Word(stringSelector.random(),spawnPointSelector.random());
    }

    @Override
    public void run() {
        spawnOnRandomSpawnPoint();
    }

    public static WordSpawner build() {
        WordRepository wordRepository = new TextFileWordRepository("src/main/java/config/wordList.txt");
        RandomNumbers randomNumberGenerator = new RandomNumberGenerator();
        StringSelector stringSelector = new StringSelectorImpl(wordRepository, randomNumberGenerator);
        SpawnPointSelector spawnPointSelector = new SpawnPointSelectorImpl(randomNumberGenerator,
                                                                           new Position(1,10),
                                                                           new Position(2,10),
                                                                           new Position(3,10));

        return new WordSpawner(stringSelector, spawnPointSelector);
    }
}

package domain;

import adapter.out.TextFileWordRepository;
import domain.port.out.WordRepository;
import util.RandomNumberGenerator;
import util.RandomNumbers;

public class WordSpawner implements Runnable{
    private final StringSelector stringSelector;
    private final SpawnPointSelector spawnPointSelector;
    private final GameField gameField;


    public WordSpawner(StringSelector stringSelector, SpawnPointSelector spawnPointSelector, GameField gameField) {
        this.stringSelector = stringSelector;
        this.spawnPointSelector = spawnPointSelector;
        this.gameField = gameField;
    }

    public Word spawnOnRandomSpawnPoint() {
        Word word = new Word(stringSelector.random(),spawnPointSelector.random());
        gameField.addWord(word);
        return word;
    }

    @Override
    public void run() {
        spawnOnRandomSpawnPoint();
    }

    public static WordSpawner build(GameField gameField) {
        WordRepository wordRepository = new TextFileWordRepository("src/main/java/config/wordList.txt");
        RandomNumbers randomNumberGenerator = new RandomNumberGenerator();
        StringSelector stringSelector = new StringSelectorImpl(wordRepository, randomNumberGenerator);
        SpawnPointSelector spawnPointSelector = new SpawnPointSelectorImpl(randomNumberGenerator,
                                                                           new Position(1,10),
                                                                           new Position(2,10),
                                                                           new Position(3,10));

        return new WordSpawner(stringSelector, spawnPointSelector, gameField);
    }
}

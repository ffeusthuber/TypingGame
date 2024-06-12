package domain;

import domain.port.out.WordRepository;
import util.RandomNumberGenerator;
import util.RandomNumbers;

public class WordSpawner{
    private final StringSelector stringSelector;
    private final GameField gameField;
    private final SpawnPointSelector spawnPointSelector;


    public WordSpawner(StringSelector stringSelector, SpawnPointSelector spawnPointSelector, GameField gameField) {
        this.stringSelector = stringSelector;
        this.spawnPointSelector = spawnPointSelector;
        this.gameField = gameField;
    }

    public WordSpawner(GameField gameField, WordRepository wordRepository) {
        this.gameField = gameField;
        RandomNumbers randomNumberGenerator = new RandomNumberGenerator();

        this.stringSelector = new StringSelectorImpl(wordRepository, randomNumberGenerator);
        this.spawnPointSelector = new SpawnPointSelectorImpl(randomNumberGenerator, gameField.getSpawnPoints());
    }

    public Word spawnOnRandomSpawnPoint() {
        Word word = new Word(stringSelector.random(),spawnPointSelector.random());
        gameField.addWord(word);
        return word;
    }

    public SpawnPointSelector getSpawnPointSelector() {
        return this.spawnPointSelector;
    }
}

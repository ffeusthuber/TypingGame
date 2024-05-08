package domain;

import adapter.in.TextFileWordRepository;
import adapter.in.WordRepository;
import adapter.out.ConsoleDisplay;
import adapter.out.DisplayAdapter;
import util.RandomNumberGenerator;
import util.RandomNumbers;

import java.util.List;

public class TypingGame {
    private static final int INITIAL_PLAYER_LIVES = 3;

    protected int playerLives;
    private WordSpawner wordSpawner;
    private DisplayAdapter display;


    public TypingGame(){
        init();
    }

    private void init(){
        this.playerLives = INITIAL_PLAYER_LIVES;
        this.wordSpawner = setupWordSpawner();
        this.display = new ConsoleDisplay();
    }

    private WordSpawner setupWordSpawner() {
        WordRepository wordRepository = new TextFileWordRepository("src/main/java/config/wordList.txt");
        RandomNumbers randomNumberGenerator = new RandomNumberGenerator();
        StringSelector stringSelector = new StringSelectorImpl(wordRepository, randomNumberGenerator);
        SpawnPointSelector spawnPointSelector = new SpawnPointSelectorImpl(randomNumberGenerator,
                                                                           new Position(1,10),
                                                                           new Position(2,10),
                                                                           new Position(3,10));

        return new WordSpawner(stringSelector, spawnPointSelector);
    }

    public void start() {
        Word word = wordSpawner.spawnOnRandomSpawnPoint();
        display.display(List.of(word));
    }

    public WordSpawner getWordSpawner() {
        return this.wordSpawner;
    }

    public DisplayAdapter getDisplay() {
        return this.display;
    }
}

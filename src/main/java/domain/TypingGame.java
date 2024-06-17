package domain;

import domain.port.in.KeyPressHandler;
import domain.port.in.KeyPressListener;
import domain.port.out.DisplayPort;
import domain.port.out.WordRepository;

import java.util.List;

public class TypingGame {
    private static final int DISPLAY_UPDATE_INTERVAL = 15;
    private static final int WORD_MOVE_INTERVAL = 15;
    private static final int WORD_MOVE_STEPSIZE = 1;
    private static int wordSpawnInterval = 2000;
    private final WordTargeter wordTargeter;


    private int playerLives;
    private final DisplayPort display;
    private final WordSpawner wordSpawner;
    private final KeyPressListener keyPressListener;
    private final TaskManager taskManager;
    private final GameField gameField;

    public TypingGame(int initialPlayerLives, DisplayPort display, WordRepository wordRepository) {
        this.playerLives = initialPlayerLives;
        this.display = display;
        this.gameField = createGameField();
        this.wordSpawner = new WordSpawner(gameField,wordRepository);
        this.wordTargeter = new WordTargeter();
        this.keyPressListener = new KeyPressHandler(gameField, wordTargeter);
        this.taskManager = new TaskManager();

        setUpTimedTasks();
    }

    private GameField createGameField() {
        int gameFieldHeight = 100;
        List<Position> spawnPoints = List.of(new Position(50, 0),
                                             new Position(300, 0),
                                             new Position(550, 0));

        return new GameField(gameFieldHeight,spawnPoints);
    }

    private void setUpTimedTasks() {
        taskManager.addTimedTasks(() -> wordSpawner.spawnOnRandomSpawnPoint(),wordSpawnInterval);
        taskManager.addTimedTasks(() -> display.display(gameField.getWords()), DISPLAY_UPDATE_INTERVAL);
        taskManager.addTimedTasks(() -> this.moveWords(WORD_MOVE_STEPSIZE),WORD_MOVE_INTERVAL);
    }

    public void start() {
        taskManager.runTimedTasks();
    }

    void moveWords(int stepSize) {
        gameField.moveWords(stepSize);

        List<Word> wordsInGameOverZone = gameField.getWordsInGameOverZone();
        for (Word word : wordsInGameOverZone) {
            playerLives -= 1;
            gameField.removeWord(word);
            if(wordTargeter.hasTarget() && wordTargeter.getTarget().equals(word)){
                wordTargeter.dropTarget();
            }
        }
    }

    public GameField getGameField() {
        return this.gameField;
    }

    public KeyPressListener getKeyPressListener() {
        return this.keyPressListener;
    }

    public int getPlayerLives() {
        return this.playerLives;
    }

    public WordTargeter getWordTargeter() {
        return this.wordTargeter;
    }
}

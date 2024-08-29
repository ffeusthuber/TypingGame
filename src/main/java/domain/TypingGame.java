package domain;

import adapter.out.TextFileWordRepository;
import domain.port.in.KeyPressListener;
import domain.port.out.DisplayPort;
import domain.port.out.WordRepository;

import java.util.List;

public class TypingGame {

    // Constants
    private static final int INITIAL_PLAYER_LIVES = 3;
    private static final int DISPLAY_UPDATE_INTERVAL = 15;
    private static final int WORD_MOVE_INTERVAL = 20;
    private static final int WORD_MOVE_STEPSIZE = 1;
    private static final int WORD_SPAWN_SPEED_UP_INTERVAL = 10000;
    private static final int MIN_WORD_SPAWN_INTERVAL = 500;
    private static final int GAME_FIELD_HEIGHT = 400;
    private static final List<Position> SPAWN_POINTS = List.of(
            new Position(50, -20),
            new Position(200, -20),
            new Position(350, -20)
    );

    // Game components
    private final WordTargeter wordTargeter;
    private final DisplayPort display;
    private final WordSpawner wordSpawner;
    private final KeyPressListener keyPressListener;
    private final TaskManager taskManager;
    private final GameField gameField;
    private final Runnable wordSpawnTask;

    //Game state
    private int wordSpawnInterval = 2000;
    private int playerLives;


    public TypingGame(DisplayPort display) {
        this(INITIAL_PLAYER_LIVES,
             display,
             new TextFileWordRepository("src/main/java/config/wordList.txt"));
    }

    public TypingGame(int initialPlayerLives, DisplayPort display, WordRepository wordRepository) {
        this.playerLives = initialPlayerLives;
        this.display = display;
        this.gameField = new GameField(GAME_FIELD_HEIGHT, SPAWN_POINTS);
        this.wordSpawner = new WordSpawner(gameField,wordRepository);
        this.wordTargeter = new WordTargeter();
        this.keyPressListener = new KeyPressHandler(gameField, wordTargeter);
        this.taskManager = new TaskManager();
        wordSpawnTask = this::spawnWord;

        setUpTimedTasks();
    }

    private void setUpTimedTasks() {
        taskManager.addTimedTasks(wordSpawnTask, wordSpawnInterval);
        taskManager.addTimedTasks(() -> this.display(), DISPLAY_UPDATE_INTERVAL);
        taskManager.addTimedTasks(() -> this.moveWords(WORD_MOVE_STEPSIZE),WORD_MOVE_INTERVAL);
        taskManager.addTimedTasks(() -> this.speedUpWordSpawning(this.taskManager), WORD_SPAWN_SPEED_UP_INTERVAL);
    }

    private void speedUpWordSpawning(TaskManager taskManager){
        taskManager.setRateForTimedTask(wordSpawnTask, decreaseWordSpawnInterval(100));
    }

    public void start() {
        taskManager.runTimedTasks();
    }

    public void stop() {
        gameField.clear();
        wordTargeter.dropTarget();
        taskManager.stopRunningTasks();
        display.gameOver();
    }

    void spawnWord(){
        wordSpawner.spawnOnRandomSpawnPoint();
    }

    void moveWords(int stepSize) {
        gameField.moveWords(stepSize);
        handleWordsInGameOverZone();
    }

    void display() {
        display.display(gameField.getWords());
    }

    private void handleWordsInGameOverZone() {
        List<Word> wordsInGameOverZone = gameField.getWordsInGameOverZone();
        for (Word word : wordsInGameOverZone) {
            playerLives -= 1;
            if(playerLives <= 0){
                stop();
            }
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

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    private int decreaseWordSpawnInterval(int decreaseValue) {
        wordSpawnInterval = Math.max(wordSpawnInterval - decreaseValue, MIN_WORD_SPAWN_INTERVAL);
        return wordSpawnInterval;
    }
}

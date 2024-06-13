package domain;

import domain.port.in.KeyPressListener;
import domain.port.in.KeyPressListenerImpl;
import domain.port.out.DisplayPort;
import domain.port.out.WordRepository;

import java.util.List;

public class TypingGame {
    private static final int INITIAL_PLAYER_LIVES = 3;
    private static final int DISPLAY_UPDATE_INTERVAL = 15;
    private static final int WORD_MOVE_INTERVAL = 15;
    private static final int WORD_MOVE_STEPSIZE = 1;
    private static int wordSpawnInterval = 2000;


    protected int playerLives;
    private final DisplayPort display;
    private final WordSpawner wordSpawner;
    private final KeyPressListener keyPressListener;
    private final TaskManager taskManager;
    private final GameField gameField;


    public TypingGame(DisplayPort display, WordRepository wordRepository) {
        this.display = display;
        this.gameField = createGameField();
        this.wordSpawner = new WordSpawner(gameField,wordRepository);
        this.keyPressListener = new KeyPressListenerImpl(gameField, new WordTargeter());
        this.playerLives = INITIAL_PLAYER_LIVES;
        this.taskManager = new TaskManager();
        setUpTimedTasks();
    }

    private GameField createGameField() {
        GameField gameField = new GameField();

        gameField.addSpawnPoint(new Position(50, 0));
        gameField.addSpawnPoint(new Position(300, 0));
        gameField.addSpawnPoint(new Position(550, 0));

        return gameField;
    }

    private void setUpTimedTasks() {
        taskManager.addTimedTasks(() -> wordSpawner.spawnOnRandomSpawnPoint(),wordSpawnInterval);
        taskManager.addTimedTasks(() -> display.display(gameField.getWords()), DISPLAY_UPDATE_INTERVAL);
        taskManager.addTimedTasks(() -> this.moveWords(WORD_MOVE_STEPSIZE,gameField.getWords()),WORD_MOVE_INTERVAL);
    }

    public void start() {
        taskManager.runTimedTasks();
    }

    public GameField getGameField() {
        return this.gameField;
    }

    public KeyPressListener getKeyPressListener() {
        return this.keyPressListener;
    }

    void moveWords(int stepSize, List<Word> words) {
        words.forEach(word -> word.moveY(stepSize));
    }
}

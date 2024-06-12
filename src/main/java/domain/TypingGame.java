package domain;

import adapter.out.TextFileWordRepository;
import application.TypingGameApplication;
import domain.port.in.KeyPressListener;
import domain.port.in.KeyPressListenerImpl;
import domain.port.out.DisplayPort;
import domain.port.out.WordRepository;

public class TypingGame {
    private static final int INITIAL_PLAYER_LIVES = 3;

    protected int playerLives;
    private WordSpawner wordSpawner;
    private DisplayPort display;
    private TaskManager taskManager;
    private GameField gameField;
    private KeyPressListener keyPressListener;

    public TypingGame(){
        init();
    }


    public TypingGame(TypingGameApplication typingGameApplication) {
        this.display = typingGameApplication;
        init();
    }

    private void init(){
        this.playerLives = INITIAL_PLAYER_LIVES;
        this.gameField = setUpGameField();
        this.wordSpawner = setUpWordSpawner();


        this.taskManager = new TaskManager();
        WordTargeter wordTargeter = new WordTargeter();
        this.keyPressListener = new KeyPressListenerImpl(gameField, wordTargeter);

        setUpTimedTasks();
    }

    private void setUpTimedTasks() {
        taskManager.addTimedTasks(() -> wordSpawner.spawnOnRandomSpawnPoint(),2000);
        taskManager.addTimedTasks(() -> display.display(gameField.getWords()), 200);
    }

    private GameField setUpGameField() {
        gameField = new GameField();

        gameField.addSpawnPoint(new Position(50, 0));
        gameField.addSpawnPoint(new Position(300, 0));
        gameField.addSpawnPoint(new Position(550, 0));

        return gameField;
    }

    private WordSpawner setUpWordSpawner() {
        WordRepository wordRepository = new TextFileWordRepository("src/main/java/config/wordList.txt");

        return new WordSpawner(gameField,wordRepository);
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
}

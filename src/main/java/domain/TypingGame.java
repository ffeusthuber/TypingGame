package domain;

import adapter.out.ConsoleDisplay;
import application.TypingGameApplication;
import domain.port.out.DisplayPort;

public class TypingGame {
    private static final int INITIAL_PLAYER_LIVES = 3;

    protected int playerLives;
    private WordSpawner wordSpawner;
    private DisplayPort display;
    private TaskManager taskManager;
    private GameField gameField;


    public TypingGame(){
        init();
    }


    public TypingGame(TypingGameApplication typingGameApplication) {
        init();
    }

    private void init(){
        this.playerLives = INITIAL_PLAYER_LIVES;
        gameField = new GameField();
        gameField.addSpawnPoint(new Position(0, 0));

        this.display = new ConsoleDisplay();
        this.wordSpawner = WordSpawner.build(gameField);
        this.taskManager = new TaskManager();

        taskManager.addTimedTasks(wordSpawner,1000);
    }

    public void start() {
        taskManager.runTimedTasks();
    }

    public GameField getGameField() {
        return this.gameField;
    }
}

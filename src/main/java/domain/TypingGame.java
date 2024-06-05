package domain;

import adapter.out.ConsoleDisplay;
import adapter.out.TextFileWordRepository;
import application.TypingGameApplication;
import domain.port.out.DisplayPort;
import domain.port.out.WordRepository;

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
        this.gameField = setUpGameField();
        this.wordSpawner = setUpWordSpawner();

        this.display = new ConsoleDisplay();
        //this.wordSpawner = WordSpawner.build(gameField);
        this.taskManager = new TaskManager();

        taskManager.addTimedTasks(wordSpawner,1000);
    }

    private GameField setUpGameField() {
        gameField = new GameField();

        gameField.addSpawnPoint(new Position(1, 0));
        gameField.addSpawnPoint(new Position(2, 0));
        gameField.addSpawnPoint(new Position(3, 0));

        return gameField;
    }

    private WordSpawner setUpWordSpawner() {
        WordRepository wordRepository = new TextFileWordRepository("src/main/java/config/wordList.txt");

        return WordSpawner.build(gameField,wordRepository);
    }

    public void start() {
        taskManager.runTimedTasks();
    }

    public GameField getGameField() {
        return this.gameField;
    }
}

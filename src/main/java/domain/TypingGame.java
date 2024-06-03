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


    public TypingGame(){
        init();
    }


    public TypingGame(TypingGameApplication typingGameApplication) {
        init();
    }

    private void init(){
        this.playerLives = INITIAL_PLAYER_LIVES;
        this.display = new ConsoleDisplay();
        this.wordSpawner = WordSpawner.build();
    }

    public void start() {
        taskManager.runTimedTasks();
    }
}

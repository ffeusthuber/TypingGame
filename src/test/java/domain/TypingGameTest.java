package domain;

import adapter.out.ConsoleDisplay;
import adapter.out.DisplayMock;
import adapter.out.WordRepositoryStub;
import domain.port.out.DisplayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Stopwatch;

import static org.assertj.core.api.Assertions.assertThat;

public class TypingGameTest {

    private static final int INITIAL_PLAYER_LIVES = 3;
    private static final int STEP_SIZE = 10;

    private TypingGame typingGame;
    private GameField gameField;

    @BeforeEach
    void setUp() {
        typingGame = initializeTypingGame();
        gameField = typingGame.getGameField();
    }

    @Test
    void whenGameIsStartedWordsGetSpawnedOnGameField() throws InterruptedException {
        typingGame.start();

        Thread.sleep(2000);

        assertThat(typingGame.getGameField().getWords()).isNotEmpty();
    }

    @Test
    void wordsMoveDownByStepSizeWhenMoved() {
        Word word = new Word("Apple", new Position(0, 0));
        gameField.addWord(word);
        int stepSize = 10;

        typingGame.moveWords(stepSize);

        Word expected = new Word("Apple", new Position(0, stepSize));
        assertThat(word).isEqualTo(expected);
    }

    @Test
    void playerLosesLifeAndWordIsRemovedWhenWordReachesGameOverZone() {
        Word word = new Word("Apple", new Position(0, gameField.getHeight() -1));
        gameField.addWord(word);

        typingGame.moveWords(STEP_SIZE);

        assertThat(typingGame.getPlayerLives()).isEqualTo(INITIAL_PLAYER_LIVES - 1);
        assertThat(gameField.getWords()).isEmpty();
    }


    @Test
    void whenPlayerLoosesLifeUpdateLivesIsCalled() {
        DisplayMock displayMock = new DisplayMock();
        TypingGame typingGame = initializeTypingGame(displayMock);
        GameField gameField = typingGame.getGameField();
        Word word = new Word("Apple",new Position(0, gameField.getHeight() -1));
        gameField.addWord(word);

        typingGame.moveWords(STEP_SIZE);

        assertThat(displayMock.isUpdateLivesCalled()).isTrue();
    }

    @Test
    void targetedWordThatMovesInGameOverZoneGetsDropped() {
        TypingGame typingGame = initializeTypingGame();
        GameField gameField = typingGame.getGameField();
        WordTargeter wordTargeter = typingGame.getWordTargeter();
        int gameFieldHeight = gameField.getHeight();
        Word word = new Word("Apple",new Position(0,gameFieldHeight-1));
        gameField.addWord(word);
        wordTargeter.targetByKey("A",gameField.getWords());

        typingGame.moveWords(10);

        assertThat(typingGame.getWordTargeter().hasTarget()).isEqualTo(false);
    }

    @Test
    void whenGameIsStoppedGameFieldIsClearedAndTargetGetsDropped() {
        GameField gameField = typingGame.getGameField();
        WordTargeter wordTargeter = typingGame.getWordTargeter();
        Word word = new Word("Apple",new Position(0,0));
        gameField.addWord(word);
        wordTargeter.targetByKey("A",gameField.getWords());
        typingGame.start();

        typingGame.stop();

        assertThat(gameField.getWords()).isEmpty();
        assertThat(wordTargeter.hasTarget()).isFalse();
    }

    @Test
    void whenGameIsStoppedRunningTasksAreStopped() {
        typingGame.start();
        assertThat(typingGame.getTaskManager().tasksRunning()).isTrue();

        typingGame.stop();
        assertThat(typingGame.getTaskManager().tasksRunning()).isFalse();
    }

    @Test
    void stopWatchIsStartedAndStoppedWithTypingGame() {
        typingGame.start();
        Stopwatch stopwatch = typingGame.getStopwatch();
        assertThat(stopwatch.isRunning()).isTrue();

        typingGame.stop();
        assertThat(stopwatch.isRunning()).isFalse();
    }


    @Test
    void whenPlayerLivesReachZeroGameIsStopped() {
        TypingGame typingGame = initializeTypingGameWith1Live();
        GameField gameField = typingGame.getGameField();
        Word word = new Word("Apple",new Position(0,gameField.getHeight()-1));
        gameField.addWord(word);

        typingGame.start();
        typingGame.moveWords(10);

        assertThat(typingGame.getTaskManager().tasksRunning()).isFalse();
    }

    private TypingGame initializeTypingGame(){
        return new TypingGame(INITIAL_PLAYER_LIVES, new ConsoleDisplay(), new WordRepositoryStub("word"));
    }

    private TypingGame initializeTypingGameWith1Live(){
        return new TypingGame(1, new ConsoleDisplay(), new WordRepositoryStub("word"));
    }

    private TypingGame initializeTypingGame(DisplayPort displayPort){
        return new TypingGame(INITIAL_PLAYER_LIVES,displayPort, new WordRepositoryStub("word"));
    }

}

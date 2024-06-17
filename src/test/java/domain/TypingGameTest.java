package domain;

import adapter.out.ConsoleDisplay;
import domain.port.out.WordRepositoryStub;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypingGameTest {

    @Test
    void whenGameIsStartedWordsGetSpawnedOnGameField() throws InterruptedException {
        TypingGame typingGame = initializeTypingGame();

        typingGame.start();
        Thread.sleep(50);

        assertThat(typingGame.getGameField().getWords()).isNotEmpty();
    }

    @Test
    void wordsAreMovedByTheCorrectStepSize() {
        TypingGame typingGame = initializeTypingGame();
        Word word = new Word("Apple", new Position(0, 0));
        GameField gameField = typingGame.getGameField();
        gameField.addWord(word);
        int stepSize = 10;

        typingGame.moveWords(stepSize);

        Word expected = new Word("Apple", new Position(0, stepSize));
        assertThat(word).isEqualTo(expected);
    }

    @Test
    void playerLosesLifeAndWordIsRemovedWhenWordReachesGameOverZone() {
        int initialPlayerLives = 3;
        TypingGame typingGame = initializeTypingGame(initialPlayerLives);
        GameField gameField = typingGame.getGameField();
        int gameFieldHeight = gameField.getHeight();
        Word word = new Word("Apple",new Position(0,gameFieldHeight-1));
        gameField.addWord(word);

        typingGame.moveWords(10);

        assertThat(typingGame.getPlayerLives()).isEqualTo(2);
        assertThat(gameField.getWords()).isEmpty();
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

    private TypingGame initializeTypingGame(){
        int initialPlayerLives = 3;
        return new TypingGame(initialPlayerLives, new ConsoleDisplay(), new WordRepositoryStub("word"));
    }
    private TypingGame initializeTypingGame(int initialPlayerLives){
        return new TypingGame(initialPlayerLives, new ConsoleDisplay(), new WordRepositoryStub("word"));
    }

}

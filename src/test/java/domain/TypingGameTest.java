package domain;

import adapter.out.ConsoleDisplay;
import domain.port.out.WordRepositoryStub;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypingGameTest {

    @Test
    void whenGameIsStartedWordsGetSpawnedOnGameField() throws InterruptedException {
        TypingGame typingGame = initializeTypingGame(3);

        typingGame.start();
        Thread.sleep(50);

        assertThat(typingGame.getGameField().getWords()).isNotEmpty();
    }

    @Test
    void wordsAreMovedByTheCorrectStepSize() {
        TypingGame typingGame = initializeTypingGame(3);
        Word word = new Word("Apple", new Position(0, 0));
        GameField gameField = typingGame.getGameField();
        gameField.addWord(word);
        int stepSize = 10;

        typingGame.moveWords(stepSize,gameField.getWords());

        Word expected = new Word("Apple", new Position(0, stepSize));
        assertThat(word).isEqualTo(expected);
    }

    @Test
    void whenAWordMovesInGameOverZoneAPlayerLiveGetsRemoved() {
        int initialPlayerLives = 3;
        TypingGame typingGame = initializeTypingGame(initialPlayerLives);
        GameField gameField = typingGame.getGameField();
        Word word = new Word("Apple",new Position(0,95));
        gameField.addWord(word);

        typingGame.moveWords(10,gameField.getWords());

        assertThat(typingGame.getPlayerLives()).isEqualTo(2);
    }

    private TypingGame initializeTypingGame(int initialPlayerLives){
        return new TypingGame(initialPlayerLives, new ConsoleDisplay(), new WordRepositoryStub("word"));
    }

    //when word in gameover
        //targeter drops target
        //gamefield removes word
        //lives -1

}

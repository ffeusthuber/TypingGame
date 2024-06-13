package domain;

import adapter.out.ConsoleDisplay;
import domain.port.out.WordRepositoryStub;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypingGameTest {

    @Test
    void whenGameIsInstantiatedPlayerGets3Lives() {
        TypingGame typingGame = new TypingGame(new ConsoleDisplay(), new WordRepositoryStub("word"));

        assertThat(typingGame.playerLives).isEqualTo(3);
    }

    @Test
    void whenGameIsStartedWordsGetSpawnedOnGameField() throws InterruptedException {
        TypingGame typingGame = new TypingGame(new ConsoleDisplay(), new WordRepositoryStub("word"));

        typingGame.start();
        Thread.sleep(50);

        assertThat(typingGame.getGameField().getWords()).isNotEmpty();
    }

    @Test
    void wordsAreMovedByTheCorrectStepSize() {
        TypingGame typingGame = new TypingGame(new ConsoleDisplay(), new WordRepositoryStub("word"));
        Word word = new Word("Apple", new Position(0, 0));
        GameField gameField = new GameField();
        gameField.addWord(word);
        int stepSize = 10;
        Word expected = new Word("Apple", new Position(0, stepSize));

        typingGame.moveWords(stepSize,gameField.getWords());

        assertThat(word).isEqualTo(expected);
    }

    //when word in gameover
        //targeter drops target
        //gamefield removes word
        //lives -1

}

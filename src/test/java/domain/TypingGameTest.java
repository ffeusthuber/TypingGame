package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypingGameTest {

    @Test
    void whenGameIsStartedPlayerGets3Lives(){
        TypingGame typingGame = new TypingGame(new WordSpawnerMock());

        typingGame.start();

        assertThat(typingGame.getPlayerLives()).isEqualTo(3);
    }

    @Test
    void whenGameIsStartedWordsGetSpawned(){
        WordSpawnerMock wordSpawner = new WordSpawnerMock();
        TypingGame typingGame = new TypingGame(wordSpawner);

        typingGame.start();

        Assertions.assertThat(wordSpawner.spawnWasCalled()).isTrue();
    }

}

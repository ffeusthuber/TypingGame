package domain;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class TypingGameTest {

    @Test
    void whenGameIsInstantiatedPlayerGets3Lives() {
        TypingGame typingGame = new TypingGame();

        assertThat(typingGame.playerLives).isEqualTo(3);
    }

    @Test
    void whenGameIsStartedWordsGetSpawnedOnGameField() throws InterruptedException {
        TypingGame typingGame = new TypingGame();

        typingGame.start();
        Thread.sleep(50);

        assertThat(typingGame.getGameField().getWords()).isNotEmpty();
    }

}

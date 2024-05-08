package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypingGameTest {

    @Test
    void whenGameIsInstantiatedPlayerGets3Lives(){
        TypingGame typingGame = new TypingGame();

        assertThat(typingGame.playerLives).isEqualTo(3);
    }

    @Test
    void whenGameIsInstantiatedWordSpawnerAndDisplayGetAssigned(){
        TypingGame typingGame = new TypingGame();

        assertThat(typingGame.getWordSpawner()).isNotNull();
        assertThat(typingGame.getDisplay()).isNotNull();
    }

}

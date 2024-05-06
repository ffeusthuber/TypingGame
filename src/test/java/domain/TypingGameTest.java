package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypingGameTest {

    @Test
    void whenGameIsInstantiatedPlayerGets3Lives(){
        TypingGame typingGame = new TypingGame();

        assertThat(typingGame.getPlayerLives()).isEqualTo(3);
    }

}

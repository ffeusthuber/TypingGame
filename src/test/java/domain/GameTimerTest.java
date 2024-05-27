package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTimerTest {
        @Test
        void gameTimerListenerGetsCalledByGameTimer() {
            GameTimerFake gameTimer = new GameTimerFake();
            GameTimerListenerMock gameTimerListener = new GameTimerListenerMock();
            gameTimer.setListener(gameTimerListener);

            gameTimer.triggerListener();

            assertThat(gameTimerListener.wasCalled()).isTrue();
    }
}

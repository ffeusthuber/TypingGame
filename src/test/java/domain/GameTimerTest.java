package domain;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTimerTest {
    @Test
    void timedGameTaskGetsRunByGameTimer() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock(latch);
        GameTimer gameTimer = new GameTimer();

        gameTimer.runTimedTask(timedGameTaskMock);
        boolean completed =latch.await(100, TimeUnit.MILLISECONDS);

        assertThat(completed).isTrue();
        assertThat(timedGameTaskMock.wasCalled()).isTrue();
    }
}

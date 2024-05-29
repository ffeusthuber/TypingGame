package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameTimerTest {

    private GameTimer gameTimer;

    @BeforeEach
    void init() {
        this.gameTimer = new GameTimer();
    }

    @AfterEach
    void cleanUp() {
        gameTimer.stop();
    }

    @Test
    void timedTaskCanBeAddedWithRate(){
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock();
        int rateForTaskInMS = 20;

        gameTimer.addTimedTasks(timedGameTaskMock, rateForTaskInMS);

        assertThat(gameTimer.getRateOfTimedTask(timedGameTaskMock)).isEqualTo(rateForTaskInMS);
    }

    @Test
    void tryingToGetRateOfTaskThatWasNotAddedOrNullThrowsErrorMessage(){
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock();

        assertThatThrownBy(() -> gameTimer.getRateOfTimedTask(timedGameTaskMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");

        assertThatThrownBy(() -> gameTimer.getRateOfTimedTask(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");
    }

    @Test
    void multipleTimedGameTaskCanBeRun() throws InterruptedException {
        CountDownLatch latchTask1 = new CountDownLatch(1);
        TimedGameTaskMock timedGameTaskMock1 = new TimedGameTaskMock(latchTask1);
        CountDownLatch latchTask2 = new CountDownLatch(1);
        TimedGameTaskMock timedGameTaskMock2 = new TimedGameTaskMock(latchTask2);

        gameTimer.addTimedTasks(timedGameTaskMock1, 20);
        gameTimer.addTimedTasks(timedGameTaskMock2, 20);
        gameTimer.start();
        boolean task1Completed =latchTask1.await(100, TimeUnit.MILLISECONDS);
        boolean task2Completed =latchTask2.await(100, TimeUnit.MILLISECONDS);

        assertThat(task1Completed).isTrue();
        assertThat(task2Completed).isTrue();
        assertThat(timedGameTaskMock1.wasCalled()).isTrue();
        assertThat(timedGameTaskMock2.wasCalled()).isTrue();
    }
}

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
        gameTimer.stopRunningTasks();
    }

    @Test
    void timedTaskCanBeAddedWithRate(){
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock();
        int rateForTaskInMs = 20;

        gameTimer.addTimedTasks(timedGameTaskMock, rateForTaskInMs);
        int actualRate = gameTimer.getRateOfTimedTask(timedGameTaskMock);

        assertThat(actualRate).isEqualTo(rateForTaskInMs);
    }

    @Test
    void tasksRunningReflectsIfTasksAreRunning() {
        assertThat(gameTimer.tasksRunning()).isFalse();

        gameTimer.runTimedTasks();
        assertThat(gameTimer.tasksRunning()).isTrue();

        gameTimer.stopRunningTasks();
        assertThat(gameTimer.tasksRunning()).isFalse();
    }

    @Test
    void rateOfTimedTaskCanBeChangedAfterStarting() {
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock();
        int initialRateForTaskInMs = 20;
        int newRateForTaskInMS = 40;
        gameTimer.addTimedTasks(timedGameTaskMock, initialRateForTaskInMs);
        gameTimer.runTimedTasks();

        gameTimer.setRateForTimedTask(timedGameTaskMock, newRateForTaskInMS);

        assertThat(gameTimer.getRateOfTimedTask(timedGameTaskMock)).isEqualTo(newRateForTaskInMS);
    }

    @Test
    void tryingToGetOrSetRateOfTimedTaskThatWasNotAddedOrNullThrowsErrorMessage(){
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock();

        assertThatThrownBy(() -> gameTimer.getRateOfTimedTask(timedGameTaskMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");

        assertThatThrownBy(() -> gameTimer.getRateOfTimedTask(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");

        assertThatThrownBy(() -> gameTimer.setRateForTimedTask(timedGameTaskMock,1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");

        assertThatThrownBy(() -> gameTimer.setRateForTimedTask(null,1))
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

        gameTimer.runTimedTasks();
        boolean task1Completed =latchTask1.await(100, TimeUnit.MILLISECONDS);
        boolean task2Completed =latchTask2.await(100, TimeUnit.MILLISECONDS);

        assertThat(task1Completed).isTrue();
        assertThat(task2Completed).isTrue();
        assertThat(timedGameTaskMock1.wasCalled()).isTrue();
        assertThat(timedGameTaskMock2.wasCalled()).isTrue();
    }

    @Test
    void runningTasksStopWhenStopped() {
        TimedGameTaskMock timedGameTaskMock1 = new TimedGameTaskMock();
        TimedGameTaskMock timedGameTaskMock2 = new TimedGameTaskMock();
        gameTimer.addTimedTasks(timedGameTaskMock1, 20);
        gameTimer.addTimedTasks(timedGameTaskMock2, 20);
        gameTimer.runTimedTasks();

        gameTimer.stopRunningTasks();

        assertThat(gameTimer.getRunningTasks().size()).isEqualTo(0);
        assertThat(gameTimer.tasksRunning()).isFalse();
    }
}

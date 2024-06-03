package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void init() {
        this.taskManager = new TaskManager();
    }

    @AfterEach
    void cleanUp() {
        taskManager.stopRunningTasks();
    }

    @Test
    void timedTaskCanBeAddedWithRate(){
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock();
        int rateForTaskInMs = 20;

        taskManager.addTimedTasks(timedGameTaskMock, rateForTaskInMs);
        int actualRate = taskManager.getRateOfTimedTask(timedGameTaskMock);

        assertThat(actualRate).isEqualTo(rateForTaskInMs);
    }

    @Test
    void tasksRunningReflectsIfTasksAreRunning() {
        assertThat(taskManager.tasksRunning()).isFalse();

        taskManager.runTimedTasks();
        assertThat(taskManager.tasksRunning()).isTrue();

        taskManager.stopRunningTasks();
        assertThat(taskManager.tasksRunning()).isFalse();
    }

    @Test
    void rateOfTimedTaskCanBeChangedAfterStarting() {
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock();
        int initialRateForTaskInMs = 20;
        int newRateForTaskInMS = 40;
        taskManager.addTimedTasks(timedGameTaskMock, initialRateForTaskInMs);
        taskManager.runTimedTasks();

        taskManager.setRateForTimedTask(timedGameTaskMock, newRateForTaskInMS);

        assertThat(taskManager.getRateOfTimedTask(timedGameTaskMock)).isEqualTo(newRateForTaskInMS);
    }

    @Test
    void tryingToGetOrSetRateOfTimedTaskThatWasNotAddedOrNullThrowsErrorMessage(){
        TimedGameTaskMock timedGameTaskMock = new TimedGameTaskMock();

        assertThatThrownBy(() -> taskManager.getRateOfTimedTask(timedGameTaskMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");

        assertThatThrownBy(() -> taskManager.getRateOfTimedTask(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");

        assertThatThrownBy(() -> taskManager.setRateForTimedTask(timedGameTaskMock, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");

        assertThatThrownBy(() -> taskManager.setRateForTimedTask(null, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No such task added");
    }

    @Test
    void multipleTimedGameTaskCanBeRun() throws InterruptedException {
        CountDownLatch latchTask1 = new CountDownLatch(1);
        TimedGameTaskMock timedGameTaskMock1 = new TimedGameTaskMock(latchTask1);
        CountDownLatch latchTask2 = new CountDownLatch(1);
        TimedGameTaskMock timedGameTaskMock2 = new TimedGameTaskMock(latchTask2);
        taskManager.addTimedTasks(timedGameTaskMock1, 20);
        taskManager.addTimedTasks(timedGameTaskMock2, 20);

        taskManager.runTimedTasks();
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
        taskManager.addTimedTasks(timedGameTaskMock1, 20);
        taskManager.addTimedTasks(timedGameTaskMock2, 20);
        taskManager.runTimedTasks();

        taskManager.stopRunningTasks();

        assertThat(taskManager.getRunningTasks().size()).isEqualTo(0);
        assertThat(taskManager.tasksRunning()).isFalse();
    }
}

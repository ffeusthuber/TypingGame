package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer {

    private final ScheduledExecutorService scheduledExecutorService;
    private final List<Runnable> tasks;

    public GameTimer(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        tasks = new ArrayList<>();
    }

    public void addTimedTasks(Runnable... tasksToAdd) {
        tasks.addAll(Arrays.asList(tasksToAdd));
    }

    public void start() {
        for (Runnable task : tasks) {
            scheduledExecutorService.schedule(task, 0, TimeUnit.MILLISECONDS);
        }
    }

    public void stop(){
        scheduledExecutorService.shutdownNow();
    }

}

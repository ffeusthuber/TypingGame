package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer {

    private final ScheduledExecutorService scheduledExecutorService;
    private final Map<Runnable,Integer> timedTasks;

    public GameTimer(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        timedTasks = new HashMap<>();
    }

    public void addTimedTasks(Runnable taskToAdd, int rateInMs) {
        timedTasks.put(taskToAdd,rateInMs);
    }

    public void start() {
        for (Map.Entry<Runnable, Integer> entry : timedTasks.entrySet()) {
            Runnable task = entry.getKey();
            int rateInMs = entry.getValue();
            scheduledExecutorService.scheduleAtFixedRate(task, 0, rateInMs, TimeUnit.MILLISECONDS);
        }
    }

    public void stop(){
        scheduledExecutorService.shutdownNow();
    }

    public int getRateOfTimedTask(Runnable task) throws IllegalArgumentException{
        if(task == null || !timedTasks.containsKey(task)) {
            throw new IllegalArgumentException("No such task added");
        }

        return timedTasks.get(task);
    }
}

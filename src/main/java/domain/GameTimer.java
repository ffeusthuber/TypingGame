package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class GameTimer {

    private final ScheduledExecutorService scheduledExecutorService;
    private final Map<Runnable,Integer> timedTasks = new HashMap<>();
    private final Map<Runnable, ScheduledFuture<?>> scheduledTasks = new HashMap<>();
    private boolean tasksRunning = false;

    public GameTimer(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void addTimedTasks(Runnable taskToAdd, int rateInMs) {
        timedTasks.put(taskToAdd,rateInMs);
    }

    public void runTimedTasks() {
        tasksRunning = true;
        for (Map.Entry<Runnable, Integer> entry : timedTasks.entrySet()) {
            Runnable task = entry.getKey();
            int rateInMs = entry.getValue();
            scheduleTask(task, rateInMs);
        }
    }

    private void scheduleTask(Runnable task, int rateInMs) {
        ScheduledFuture<?> scheduledTask = scheduledExecutorService.scheduleAtFixedRate(task, 0, rateInMs, TimeUnit.MILLISECONDS);
        scheduledTasks.put(task, scheduledTask);
    }

    public void stopTimedTasks(){
        scheduledExecutorService.shutdownNow();
        tasksRunning = false;
    }

    public int getRateOfTimedTask(Runnable task) throws IllegalArgumentException{
        checkValidity(task);

        return timedTasks.get(task);
    }

    public void setRateForTimedTask(Runnable task, int rateInMs) {
        checkValidity(task);

        if (scheduledTasks.containsKey(task)) {
            ScheduledFuture<?> scheduledTask = scheduledTasks.get(task);
            scheduledTask.cancel(false);
            scheduledTasks.remove(task);
        }

        timedTasks.put(task, rateInMs);
        if (tasksRunning) {
            scheduleTask(task,rateInMs);
        }
    }

    private void checkValidity(Runnable task) {
        if (task == null || !timedTasks.containsKey(task)) {
            throw new IllegalArgumentException("No such task added");
        }
    }

    public boolean tasksRunning() {
        return tasksRunning;
    }
}

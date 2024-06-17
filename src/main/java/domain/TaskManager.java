package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TaskManager {

    private final ScheduledExecutorService scheduledExecutorService;
    private final Map<Runnable,Integer> timedTasks = new HashMap<>();
    private final Map<Runnable, ScheduledFuture<?>> runningTasks = new HashMap<>();
    private boolean tasksRunning = false;

    public TaskManager(){
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
            runTask(task, rateInMs);
        }
    }

    public void stopRunningTasks(){
        for (ScheduledFuture<?> task : runningTasks.values()) {
            task.cancel(true);
        }
        scheduledExecutorService.shutdownNow();
        runningTasks.clear();
        tasksRunning = false;
    }

    public int getRateOfTimedTask(Runnable task) throws IllegalArgumentException{
        checkValidity(task);

        return timedTasks.get(task);
    }

    public void setRateForTimedTask(Runnable task, int rateInMs) {
        checkValidity(task);

        if (runningTasks.containsKey(task)) {
            ScheduledFuture<?> scheduledTask = runningTasks.get(task);
            scheduledTask.cancel(false);
            runningTasks.remove(task);
        }

        timedTasks.put(task, rateInMs);
        if (tasksRunning) {
            runTask(task, rateInMs);
        }
    }

    private void runTask(Runnable task, int rateInMs) {
        ScheduledFuture<?> scheduledTask = scheduledExecutorService.scheduleAtFixedRate(task, 0, rateInMs, TimeUnit.MILLISECONDS);
        runningTasks.put(task, scheduledTask);
    }

    private void checkValidity(Runnable task) {
        if (task == null || !timedTasks.containsKey(task)) {
            throw new IllegalArgumentException("No such task added");
        }
    }

    public boolean tasksRunning() {
        return tasksRunning;
    }

    public Map<Runnable, ScheduledFuture<?>> getRunningTasks() {
        return this.runningTasks;
    }
}

package domain;

import java.util.concurrent.*;

public class TaskManager {

    private final ScheduledExecutorService scheduledExecutorService;
    private final ConcurrentHashMap<Runnable,Integer> timedTasks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Runnable, ScheduledFuture<?>> runningTasks = new ConcurrentHashMap<>();

    public TaskManager(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void addTimedTasks(Runnable taskToAdd, int rateInMs) {
        timedTasks.put(taskToAdd,rateInMs);
    }

    public void runTimedTasks() {
        timedTasks.forEach((task, rateInMs) -> runTask(task, rateInMs, 0));
    }

    public void stopRunningTasks(){
        try {
            runningTasks.values().forEach(task -> task.cancel(true));
            scheduledExecutorService.shutdownNow();
        } finally {
            runningTasks.clear();
        }
    }

    public int getRateOfTimedTask(Runnable task) throws IllegalArgumentException{
        if(task != null && timedTasks.containsKey(task)){
            return timedTasks.get(task);
        }
        throw new IllegalArgumentException("Task not found");
    }

    public void setRateForTimedTask(Runnable task, int rateInMs) {
        if (task != null && timedTasks.containsKey(task)) {
            timedTasks.put(task, rateInMs);
            if (tasksRunning()) {
                cancelRunningTask(task);
                runTask(task, rateInMs, rateInMs);
            }
        } else {
            throw new IllegalArgumentException("Task not found");
        }
    }

    private void cancelRunningTask(Runnable task) {
        runningTasks.get(task).cancel(false);
        runningTasks.remove(task);
    }

    private void runTask(Runnable task, int rateInMs, int initialDelay) {
        try {
            ScheduledFuture<?> scheduledTask = scheduledExecutorService.scheduleAtFixedRate(task, initialDelay, rateInMs, TimeUnit.MILLISECONDS);
            runningTasks.put(task, scheduledTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean tasksRunning() {
        return !runningTasks.isEmpty();
    }
}
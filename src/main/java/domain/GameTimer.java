package domain;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    private final Timer timer;

    public GameTimer(){
        timer = new Timer();
    }

    public void runTimedTask(TimerTask timerTask) {
        timer.schedule(timerTask,0);
    }
}

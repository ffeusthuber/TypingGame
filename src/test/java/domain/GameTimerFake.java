package domain;

public class GameTimerFake implements GameTimer {

    private GameTimerListener gameTimerListener;

    @Override
    public void setListener(GameTimerListener gameTimerListener) {
        this.gameTimerListener = gameTimerListener;
    }

    public void triggerListener() {
        gameTimerListener.onTimerReachedZero();
    }
}

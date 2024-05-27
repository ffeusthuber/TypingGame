package domain;

public class GameTimerListenerMock implements GameTimerListener {
    private boolean wasCalled;

    public boolean wasCalled() {
        return wasCalled;
    }

    @Override
    public void onTimerReachedZero() {
        wasCalled = true;
    }
}

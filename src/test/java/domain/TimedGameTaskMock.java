package domain;

import java.util.concurrent.CountDownLatch;

public class TimedGameTaskMock implements Runnable {
    private CountDownLatch latch;
    private boolean wasCalled = false;

    public TimedGameTaskMock() {

    }

    public TimedGameTaskMock(CountDownLatch latch) {
        this.latch = latch;
    }

    public boolean wasCalled() {
        return wasCalled;
    }

    @Override
    public void run() {
        wasCalled = true;
        latch.countDown();
    }
}

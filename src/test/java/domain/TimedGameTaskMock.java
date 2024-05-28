package domain;

import java.util.concurrent.CountDownLatch;

public class TimedGameTaskMock implements Runnable {
    private final CountDownLatch latch;
    private boolean wasCalled = false;

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

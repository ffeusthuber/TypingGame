package util;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopwatchTest {

    @Test
    void testStopwatch() {
        MutableClock clock = new MutableClock(Instant.EPOCH, Clock.systemDefaultZone().getZone());
        Stopwatch stopwatch = new Stopwatch(clock);
        TemporalAmount runTime = Duration.ofSeconds(10);

        stopwatch.start();
        clock.fastForward(runTime);
        stopwatch.stop();

        assertEquals(runTime, stopwatch.getElapsedTime());
    }
}

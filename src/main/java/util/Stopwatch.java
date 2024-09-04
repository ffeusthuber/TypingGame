package util;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

public class Stopwatch {
    private final Clock clock;
    private TemporalAmount elapsedTime = Duration.ZERO;
    private Instant startInstant;
    boolean isRunning = false;

    public Stopwatch(Clock clock) {
        this.clock = clock;
    }

    public void start() {
        startInstant = clock.instant();
        isRunning = true;
    }

    public void stop() {
        Instant stopInstant = clock.instant();
        isRunning = false;
        this.elapsedTime = Duration.between(startInstant, stopInstant);
    }

    public TemporalAmount getElapsedTime() {
        if(isRunning) {
            return Duration.between(startInstant, clock.instant());
        }
        return this.elapsedTime;
    }

    public boolean isRunning() {
        return isRunning;
    }
}

package util;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;

public class MutableClock extends Clock {
    private final ZoneId zoneId;
    private Instant instant;

    public MutableClock(Instant instant, ZoneId zoneId) {
        this.instant = instant;
        this.zoneId = zoneId;
    }

    @Override
    public ZoneId getZone() {
        return this.zoneId;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return new MutableClock(Instant.EPOCH,zone);
    }

    @Override
    public Instant instant() {
        return this.instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public void fastForward(TemporalAmount temporalAmount) {
        setInstant(this.instant.plus(temporalAmount));
    }
}

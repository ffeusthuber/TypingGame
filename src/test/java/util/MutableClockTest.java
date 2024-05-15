package util;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

public class MutableClockTest {

    @Test
    void testFastForward(){
        MutableClock mutableClock = new MutableClock(Instant.EPOCH, ZoneId.systemDefault());

        mutableClock.fastForward(Duration.ofSeconds(5));

        assertThat(mutableClock.instant()).isEqualTo(Instant.EPOCH.plusSeconds(5));
    }
}

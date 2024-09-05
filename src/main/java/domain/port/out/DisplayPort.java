package domain.port.out;

import domain.Word;

import java.time.temporal.TemporalAmount;
import java.util.List;

public interface DisplayPort {
    void display(List<Word> words);
    void display(TemporalAmount stopwatchTime);
    void gameOver();
    void updateLives();
}

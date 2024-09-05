package adapter.out;

import domain.Word;
import domain.port.out.DisplayPort;

import java.time.temporal.TemporalAmount;
import java.util.List;

public class ConsoleDisplay implements DisplayPort {
    @Override
    public void display(List<Word> words) {
        for (Word word : words) {
            System.out.println(word);
        }
    }

    @Override
    public void display(TemporalAmount stopwatchTime) {
        System.out.println("Stopwatch time: " + stopwatchTime);
    }

    @Override
    public void gameOver() {
        System.out.println("GAME OVER");
    }

    @Override
    public void updateLives() {
        System.out.println("Lives updated");
    }
}

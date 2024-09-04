package domain.port.out;

import domain.Word;

import java.util.List;

public interface DisplayPort {
    void display(List<Word> words);
    void gameOver();
    void updateLives();
}

package adapter.out;

import domain.Word;
import domain.port.out.DisplayPort;

import java.util.List;

public class DisplayMock implements DisplayPort {

    boolean updateLivesCalled = false;

    private List<Word> displayedWords;

    @Override
    public void display(List<Word> words) {
        this.displayedWords = words;
    }

    @Override
    public void gameOver() {
        System.out.println("GAME OVER");
    }

    @Override
    public void updateLives() {
        updateLivesCalled = true;
        System.out.println("Lives updated");
    }

    public List<Word> getDisplayedWords() {
        return this.displayedWords;
    }

    public boolean isUpdateLivesCalled() {
        return updateLivesCalled;
    }
}

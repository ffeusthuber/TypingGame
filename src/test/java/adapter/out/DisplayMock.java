package adapter.out;

import domain.Word;

import java.util.List;

public class DisplayMock implements DisplayAdapter {

    private List<Word> displayedWords;

    @Override
    public void display(List<Word> words) {
        this.displayedWords = words;
    }

    public List<Word> getDisplayedWords() {
        return this.displayedWords;
    }
}

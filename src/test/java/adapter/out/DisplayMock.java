package adapter.out;

import domain.Word;

public class DisplayMock implements DisplayAdapter {

    private Word displayedWord;

    @Override
    public void display(Word word) {
        this.displayedWord = word;
    }

    public Word getDisplayedWord() {
        return this.displayedWord;
    }
}

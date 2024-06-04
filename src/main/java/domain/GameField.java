package domain;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private final List<Word> words = new ArrayList<>();
    public void addWord(Word word) {
        words.add(word);
    }

    public List<Word> getWords() {
        return this.words;
    }
}

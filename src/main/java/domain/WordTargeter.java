package domain;

import java.util.List;

public class WordTargeter {

    private Word target = null;

    public void targetByKey(String key, List<Word> wordsOnScreen) {
       this.target = wordsOnScreen.stream()
                .filter(word -> word.getRemainingWord().startsWith(key))
                .findFirst()
               .orElse(null);
    }

    public Word getTarget() {
        return this.target;
    }

    public boolean hasTarget() {
        return this.target != null;
    }

    public void dropTarget() {
        this.target = null;
    }
}

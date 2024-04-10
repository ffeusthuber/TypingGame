public class Word {
    private final String word;
    private String remainingWord;
    private boolean typed;

    public Word(String word) {
        if (word.isEmpty()) {
            throw new IllegalArgumentException("Word must not be empty");
        }

        this.word = word;
        this.remainingWord = word;
        this.typed = false;
    }

    public void type(String letter) {
        if (remainingWord.startsWith(letter)) {
            remainingWord = remainingWord.substring(1);
            if (remainingWord.isEmpty()) {
                typed = true;
            }
        }
    }

    private String getWord() {
        return this.word;
    }

    public String getRemainingWord() {
        return this.remainingWord;
    }

    public boolean isTyped() {
        return typed;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if (!(obj instanceof Word))
            return false;
        Word otherWord = (Word) obj;
        return otherWord.getWord().equals(this.word);
    }
}

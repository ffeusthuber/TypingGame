public class Word {
    private final String word;
    private String remainingWord;

    public Word(String word) {
        this.word = word;
        this.remainingWord = word;
    }

    public Status getState() {
        return Status.NOT_TYPED;
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

    private String getWord() {
        return this.word;
    }

    public void type(String letter) {
        if(remainingWord.startsWith(letter)){
            remainingWord = remainingWord.substring(1);
        }
    }

    public String getRemainingWord() {
        return this.remainingWord;
    }
}

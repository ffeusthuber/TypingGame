public class Word {
    private final String word;
    public Word(String word) {
        this.word = word;
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
}

public class Word {
    private final String word;
    private String remainingWord;
    private State state;

    public Word(String word) {
        if (word.isEmpty()) {
            throw new IllegalArgumentException("Word must not be empty");
        }

        this.word = word;
        this.remainingWord = word;
        this.state = State.NOT_TYPED;
    }

    public State getState() {
        return this.state;
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
            if(remainingWord.length() > 1){
                remainingWord = remainingWord.substring(1);
            } else if (remainingWord.length() == 1) {
                remainingWord = "";
                state = State.TYPED;
            }
        }
    }

    public String getRemainingWord() {
        return this.remainingWord;
    }
}

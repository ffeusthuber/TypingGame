package domain;

public class Word {
    private final String word;
    private String remainingWord;
    private boolean typed;
    private Position position;


    public Word(String word) {
        this(word, new Position(0,0));
    }

    public Word(String word,Position position) {
        if (word.isEmpty()) {
            throw new IllegalArgumentException("Word must not be empty");
        }

        this.word = word;
        this.position = position;
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

    protected Position getPosition() {
        return  this.position;
    }

    public void moveDown(int stepSize) {
        this.position = new Position(this.position.x(), this.position.y()-stepSize);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if (!(obj instanceof Word))
            return false;
        Word otherWord = (Word) obj;
        return (otherWord.getWord().equals(this.word) &&
                otherWord.getPosition().equals(this.position));
    }

    @Override
    public String toString() {
        return this.remainingWord + " - " + this.position;
    }

}

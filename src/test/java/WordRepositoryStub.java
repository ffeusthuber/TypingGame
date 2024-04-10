public class WordRepositoryStub implements WordRepository {
    @Override
    public Word getWordByNumber(int wordNumber) {
        if(wordNumber >= getNumberOfWords()){
            throw new IndexOutOfBoundsException("Repository contains " + getNumberOfWords() + " word(s). " + wordNumber + " is out of range.");
        }
        return new Word("Apple");
    }

    @Override
    public int getNumberOfWords() {
        return 1;
    }
}

public class WordSelector {
    private final WordRepository wordRepository;
    public WordSelector(WordRepository wordRepository) {

        this.wordRepository = wordRepository;
    }

    public Word select(int wordNumber) {
       return wordRepository.getWordByNumber(wordNumber);
    }
}

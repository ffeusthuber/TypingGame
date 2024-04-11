public class WordSelector {
    private final WordRepository wordRepository;
    private final RandomNumbers randomNumberGenerator;
    private final int numberOfWords;

    public WordSelector(WordRepository wordRepository, RandomNumbers randomNumberGenerator) {
        this.wordRepository = wordRepository;
        this.randomNumberGenerator = randomNumberGenerator;

        numberOfWords = wordRepository.getNumberOfWords();
    }

    public String random() {
        return wordRepository.getWordByIndex(
                randomNumberGenerator.nextInt(numberOfWords-1));
    }
}

public class WordSelector {
    private final WordRepository wordRepository;
    private final RandomNumbers randomNumberGenerator;

    public WordSelector(WordRepository wordRepository, RandomNumbers randomNumberGenerator) {
        this.wordRepository = wordRepository;
        this.randomNumberGenerator = randomNumberGenerator;

        randomNumberGenerator.setUpperLimit(wordRepository.getNumberOfWords()-1);
    }

    public Word random() {
        return wordRepository.getWordByIndex(randomNumberGenerator.nextInt());
    }
}

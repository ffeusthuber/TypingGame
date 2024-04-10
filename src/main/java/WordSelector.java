public class WordSelector {
    private final WordRepository wordRepository;
    private final RandomNumbers randomNumberGenerator;

    public WordSelector(WordRepository wordRepository, RandomNumbers randomNumberGenerator) {
        this.wordRepository = wordRepository;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public Word random() {
        return wordRepository.getWordByIndex(randomNumberGenerator.nextInt());
    }
}

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSelectorTest {

    @Test
    void randomWordGetsReturned(){
        WordRepositoryStub wordRepository = new WordRepositoryStub();
        RandomNumbersStub randomNumberGenerator = new RandomNumbersStub(wordRepository.getNumberOfWords());
        WordSelector wordSelector = new WordSelector(wordRepository, randomNumberGenerator);
        Word expected = new Word("Banana");

        Word actual = wordSelector.random();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void randomNumberGeneratorGetsLimitedToNumberOfWordsInRepository() {
        WordRepositoryStub wordRepository = new WordRepositoryStub();
        RandomNumbersStub randomNumberGenerator = new RandomNumbersStub(wordRepository.getNumberOfWords());
        //TODO refactor so limit gets automatically set

        new WordSelector(wordRepository, randomNumberGenerator);

        assertThat(wordRepository.getNumberOfWords()).isEqualTo(randomNumberGenerator.getUpperLimit());
    }
}

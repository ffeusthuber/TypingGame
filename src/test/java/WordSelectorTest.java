import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSelectorTest {

    private WordRepositoryStub wordRepository;
    private RandomNumbersStub randomNumberGenerator;
    private WordSelector wordSelector;

    @BeforeEach
    void setup(){
         wordRepository = new WordRepositoryStub(
                 "Apple",
                 "Banana");

        randomNumberGenerator = new RandomNumbersStub();
        wordSelector = new WordSelector(wordRepository, randomNumberGenerator);
    }

    @Test
    void randomWordGetsReturned(){
        String expected = "Banana";

        String actual = wordSelector.random();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void randomNumberGeneratorGetsLimitedToNumberOfWordsInRepository() {
        assertThat(wordRepository.getNumberOfWords()-1).isEqualTo(randomNumberGenerator.getUpperLimit());
    }
}

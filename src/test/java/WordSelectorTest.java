import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSelectorTest {

    private WordRepository wordRepositoryStub;
    private RandomNumbersStub randomNumbersStub;
    private WordSelector wordSelector;

    @BeforeEach
    void setup(){
         wordRepositoryStub = new WordRepositoryStub(
                 "Apple",
                 "Banana");

        randomNumbersStub = new RandomNumbersStub();
        wordSelector = new WordSelector(wordRepositoryStub, randomNumbersStub);
    }

    @Test
    void randomWordGetsReturned(){
        String expected = "Banana";

        String actual = wordSelector.random();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void randomNumberGeneratorGetsLimitedToNumberOfWordsInRepository() {
        assertThat(wordRepositoryStub.getNumberOfWords()-1).isEqualTo(randomNumbersStub.getUpperLimit());
    }
}

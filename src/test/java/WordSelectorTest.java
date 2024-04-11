import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSelectorTest {
    private WordSelector wordSelector;
    private RandomNumbersStub randomNumbersStub;
    private WordRepository wordRepositoryStub;

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
    void upperLimitPassedInRandomNumbersIsNumberOfWordsInRepositoryMinus1(){
        wordSelector.random();

        assertThat(randomNumbersStub.getUpperLimit()).isEqualTo(wordRepositoryStub.getNumberOfWords()-1);
    }
}

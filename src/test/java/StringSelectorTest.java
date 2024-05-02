import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSelectorTest {
    private StringSelector stringSelector;
    private RandomNumbersStub randomNumbersStub;

    @BeforeEach
    void setup(){
        WordRepository wordRepositoryStub = new WordRepositoryStub(
                "Apple",
                "Banana");

        randomNumbersStub = new RandomNumbersStub();
        stringSelector = new StringSelector(wordRepositoryStub, randomNumbersStub);
    }

    @Test
    void whenStringSelectorIsInstantiatedWordListGetsPopulatedFromRepository(){
        assertThat(stringSelector.getWordList())
                .isEqualTo(List.of("Apple","Banana"));
    }

    @Test
    void randomWordGetsReturned(){
        String expected = "Banana";

        String actual = stringSelector.random();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void upperLimitPassedInRandomNumbersIsSizeOfWordListMinus1(){
        stringSelector.random();

        assertThat(randomNumbersStub.getUpperLimit()).isEqualTo(stringSelector.getWordList().size()-1);
    }
}

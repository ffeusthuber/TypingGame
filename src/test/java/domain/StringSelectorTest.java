package domain;

import adapter.in.WordRepository;
import adapter.in.WordRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.RandomNumbersStub;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSelectorTest {
    private StringSelectorImpl stringSelector;
    private RandomNumbersStub randomNumbersStub;

    @BeforeEach
    void setup(){
        WordRepository wordRepositoryStub = new WordRepositoryStub(
                "Apple",
                "Banana");

        randomNumbersStub = new RandomNumbersStub(1);
        stringSelector = new StringSelectorImpl(wordRepositoryStub, randomNumbersStub);
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
    void upperLimitPassedInRandomNumbersIsSetToNumberOfWords(){
        stringSelector.random();

        assertThat(randomNumbersStub.getUpperLimit()).isEqualTo(stringSelector.getWordList().size());
    }
}

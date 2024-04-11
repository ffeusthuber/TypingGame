import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordRepositoryTest {
    @Test
    void givenValidIntegerAWordGetsReturned(){
        String expected = "Apple";
        WordRepository wordRepositoryStub = new WordRepositoryStub(expected);
        int wordIndex = 0;

        String actual = wordRepositoryStub.getWordByIndex(wordIndex);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenAnIntegerOutOfRangeAnExceptionIsThrown(){
        WordRepository wordRepository = new WordRepositoryImpl();
        int wordIndex = wordRepository.getNumberOfWords() + 1;

        assertThatThrownBy(() -> wordRepository.getWordByIndex(wordIndex))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Repository contains " + wordRepository.getNumberOfWords() + " word(s). Index of " + wordIndex + " is out of range.");
    }
}

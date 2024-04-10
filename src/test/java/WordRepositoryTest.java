import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordRepositoryTest {
    @Test
    void givenValidIntegerAWordGetsReturned(){
        Word expected = new Word("Apple");
        WordRepository wordRepositoryStub = new WordRepositoryStub();
        int wordIndex = 0;

        Word actual = wordRepositoryStub.getWordByNumber(wordIndex);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenAnIntegerOutOfRangeAnExceptionIsThrown(){
        WordRepository wordRepositoryStub = new WordRepositoryStub();
        int wordIndex = 999;

        assertThatThrownBy(() -> wordRepositoryStub.getWordByNumber(wordIndex))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Repository contains " + wordRepositoryStub.getNumberOfWords() + " word(s). " + wordIndex + " is out of range.");
    }
}

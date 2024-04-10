import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordRepositoryTest {
    @Test
    void givenAnIntegerAWordGetsReturned(){
        Word expected = new Word("Apple");
        WordRepository wordRepositoryStub = new WordRepositoryStub();
        int wordNumber = 0;

        Word actual = wordRepositoryStub.getWordByNumber(wordNumber);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenAnIntegerOutOfRangeAnExceptionIsThrown(){
        WordRepository wordRepositoryStub = new WordRepositoryStub();
        int wordNumber = 999;

        assertThatThrownBy(() -> wordRepositoryStub.getWordByNumber(wordNumber))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Repository contains " + wordRepositoryStub.getNumberOfWords() + " word(s). " + wordNumber + " is out of range.");
    }
}

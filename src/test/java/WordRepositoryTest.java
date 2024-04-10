import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordRepositoryTest {
    @Test
    void givenAnIntegerAWordGetsReturned(){
        Word expected = new Word("Apple");
        WordRepository wordRepositoryStub = new WordRepositoryStub();

        int wordNumber = 0;
        Word actual = wordRepositoryStub.getWordByNumber(wordNumber);

        assertThat(actual).isEqualTo(expected);
    }
}

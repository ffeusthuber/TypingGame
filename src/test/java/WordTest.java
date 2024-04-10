import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordTest {
    @Test
    void wordsAreEqualWhenTheirStringsMatch(){
        Word word1 = new Word("Apple");
        Word word2 = new Word("Apple");

        assertThat(word1).isEqualTo(word2);
    }
}

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordTest {
    @Test
    void wordsAreEqualWhenTheirStringsMatch(){
        Word word1 = new Word("Apple");
        Word word2 = new Word("Apple");

        assertThat(word1).isEqualTo(word2);
    }

    @Test
    void typingCorrectLetterShortensTheRemainingWord(){
        Word word = new Word("Apple");

        word.type("A");
        String remaining = word.getRemainingWord();

        assertThat(remaining).isEqualTo("pple");
    }

    @Test
    void typingIncorrectLetterDoesNotChangeTheRemainingWord(){
        Word word = new Word("Apple");

        word.type("Z");
        String remaining = word.getRemainingWord();

        assertThat(remaining).isEqualTo("Apple");
    }
}

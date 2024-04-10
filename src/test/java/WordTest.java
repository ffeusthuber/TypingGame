
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordTest {
    @Test
    void newlyCreatedWordShouldHaveNotTypedState() {
        Word word = new Word("Apple");

        assertThat(word.getState()).isEqualTo(State.NOT_TYPED);
    }

    @Test
    void creatingWordWithEmptyStringShouldThrowException() {
        assertThatThrownBy(() -> new Word(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Word must not be empty");
    }

    @Test
    void wordsWithSameStringShouldBeEqual(){
        Word word1 = new Word("Apple");
        Word word2 = new Word("Apple");

        assertThat(word1).isEqualTo(word2);
    }

    @Test
    void typingCorrectLetterShouldShortenRemainingWord(){
        Word word = new Word("Apple");

        word.type("A");

        assertThat(word.getRemainingWord()).isEqualTo("pple");
    }

    @Test
    void typingIncorrectLetterShouldNotChangeRemainingWord(){
        Word word = new Word("Apple");

        word.type("Z");

        assertThat(word.getRemainingWord()).isEqualTo("Apple");
    }

    @Test
    void typingEveryLetterOfWordInOrderShouldSetStateToTyped(){
        Word word = new Word("App");

        word.type("A");
        word.type("p");
        word.type("p");

        assertThat(word.getState()).isEqualTo(State.TYPED);
    }
}

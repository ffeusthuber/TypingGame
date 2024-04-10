
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordTest {
    @Test
    void newlyCreatedWordGetsAssignedNotTypedState() {
        Word word = new Word("Apple");

        assertThat(word.getState()).isEqualTo(State.NOT_TYPED);
    }

    @Test
    void creatingAWordWithAnEmptyStringResultsInAnException() {
        assertThatThrownBy(() -> new Word(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Word must not be empty");
    }

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

    @Test
    void whenEveryLetterInWordIsTypedInOrderTheStateIsSetToTyped(){
        Word word = new Word("App");

        word.type("A");
        word.type("p");
        word.type("p");

        assertThat(word.getState()).isEqualTo(State.TYPED);
    }
}

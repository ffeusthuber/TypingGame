package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordTest {
    private final Position spawnPosition = new Position(0,0);
    @Test
    void newlyCreatedWordShouldHaveNotTypedState() {
        Word word = new Word("Apple", spawnPosition);

        assertThat(word.isTyped()).isFalse();
    }

    @Test
    void creatingWordWithEmptyStringShouldThrowException() {
        assertThatThrownBy(() -> new Word("",spawnPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Word must not be empty");
    }

    @Test
    void wordsWithSameStringAndPositionShouldBeEqual(){
        Word word1 = new Word("Apple",spawnPosition);
        Word word2 = new Word("Apple",spawnPosition);

        assertThat(word1).isEqualTo(word2);
    }

    @Test
    void typingCorrectLetterShouldShortenRemainingWord(){
        Word word = new Word("Apple", spawnPosition);

        word.type("A");

        assertThat(word.getRemainingWord()).isEqualTo("pple");
    }

    @Test
    void typingIncorrectLetterShouldNotChangeRemainingWord(){
        Word word = new Word("Apple", spawnPosition);

        word.type("Z");

        assertThat(word.getRemainingWord()).isEqualTo("Apple");
    }

    @Test
    void typingEveryLetterOfWordInOrderShouldSetStateToTyped(){
        Word word = new Word("App",spawnPosition);

        word.type("A");
        word.type("p");
        word.type("p");

        assertThat(word.isTyped()).isTrue();
    }

    @Test
    void moveDownReducesYPositionByGivenStepSize(){
        Word word = new Word("Apple",new Position(1,10));

        word.moveDown(2);

        assertThat(word.getPosition().y()).isEqualTo(8);
    }
}

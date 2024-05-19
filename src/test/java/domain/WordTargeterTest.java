package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WordTargeterTest {

    @Test
    void whenFirstCharOfWordIsEnteredWordIsTargeted() {
        Word expected = new Word("Apple");
        List<Word> wordsOnScreen = new ArrayList<>();
        wordsOnScreen.add(expected);
        wordsOnScreen.add(new Word("Banana"));
        WordTargeter wordTargeter = new WordTargeter();


        wordTargeter.targetByKey("A", wordsOnScreen);
        Word actual = wordTargeter.getTarget();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenNoWordOnScreenStartsWithCharNoWordIsTargeted() {
        List<Word> wordsOnScreen = new ArrayList<>();
        wordsOnScreen.add(new Word("Apple"));
        wordsOnScreen.add(new Word("Banana"));
        WordTargeter wordTargeter = new WordTargeter();


        wordTargeter.targetByKey("Z", wordsOnScreen);
        Word actual = wordTargeter.getTarget();

        assertThat(actual).isNull();
    }
}

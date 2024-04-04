import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WordTyperTest {
    @Test
    void newlyCreatedWordGetsAssignedNotTypedState() {
        Word word = new Word("A");

        assertThat(word.getState()).isEqualTo(Status.NOT_TYPED);
    }

}

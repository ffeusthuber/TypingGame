import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSelectionTest {
    @Test
    void wordGetsReturned(){
        Word expected = new Word("Apple");
        WordRepository wordRepositoryStub = new WordRepositoryStub();
        WordSelector wordSelector = new WordSelector(wordRepositoryStub);

        Word actual = wordSelector.select(0);

        assertThat(actual).isEqualTo(expected);
    }
}

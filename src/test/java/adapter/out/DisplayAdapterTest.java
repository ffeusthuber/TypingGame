package adapter.out;

import domain.Position;
import domain.Word;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DisplayAdapterTest {
    @Test
    void wordGetsDisplayed(){
        DisplayMock displayAdapter = new DisplayMock();
        Word word = new Word("Banana", new Position(1,1));

        displayAdapter.display(word);

        assertThat(displayAdapter.getDisplayedWord()).isEqualTo(word);
    }
}

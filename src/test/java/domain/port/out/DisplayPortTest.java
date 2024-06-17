package domain.port.out;

import adapter.out.DisplayMock;
import domain.Position;
import domain.Word;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DisplayPortTest {
    @Test
    void wordsGetDisplayed(){
        DisplayMock displayAdapter = new DisplayMock();
        List<Word> words = List.of(
                new Word("Banana", new Position(1,1)),
                new Word("Banana", new Position(1,1)));

        displayAdapter.display(words);

        assertThat(displayAdapter.getDisplayedWords()).isEqualTo(words);
    }
}

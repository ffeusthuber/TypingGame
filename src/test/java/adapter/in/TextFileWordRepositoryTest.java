package adapter.in;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TextFileWordRepositoryTest {

    @Test
    void getAllReturnsAllStringsFromTxtFile(){
        WordRepository textFileWordRepository = new TextFileWordRepository("src/test/java/config/testWordList.txt");

        List<String> expected = List.of("Apple", "Banana", "Orange");
        List<String> actual = textFileWordRepository.getAll();

        assertThat(actual).isEqualTo(expected);
    }
}

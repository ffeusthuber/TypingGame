package adapter.out;

import domain.Position;
import domain.Word;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleDisplayTest {
    @Test
    void displayPrintsEachWordInConsole() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        ConsoleDisplay consoleDisplay = new ConsoleDisplay();
        List<Word> words = new ArrayList<>();
        words.add(new Word("Apple", new Position(1, 1)));
        words.add(new Word("Banana", new Position(2, 2)));
        words.add(new Word("Orange", new Position(3, 3)));

        consoleDisplay.display(words);

        String[] outputLines = outputStreamCaptor.toString().split(System.lineSeparator());
        assertEquals("Apple - Position[x=1, y=1]", outputLines[0].trim());
        assertEquals("Banana - Position[x=2, y=2]", outputLines[1].trim());
        assertEquals("Orange - Position[x=3, y=3]", outputLines[2].trim());
    }
}

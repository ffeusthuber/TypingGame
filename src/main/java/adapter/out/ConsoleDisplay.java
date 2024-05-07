package adapter.out;

import domain.Word;

import java.util.List;

public class ConsoleDisplay implements DisplayAdapter{
    @Override
    public void display(List<Word> words) {
        for (Word word : words) {
            System.out.println(word);
        }
    }
}

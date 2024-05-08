package adapter.in;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileWordRepository implements WordRepository {
    private final File textFile;

    public TextFileWordRepository(String filePath) {
        this.textFile = new File(filePath);
    }


    @Override
    public List<String> getAll() {
        return extractWordsFromFile();
    }

    private List<String> extractWordsFromFile() {
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }
        return words;
    }
}

package adapter.out;

import domain.port.out.WordRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordRepositoryStub implements WordRepository {
    private final ArrayList<String> words;

    public WordRepositoryStub(String... words) {
        this.words = new ArrayList<>(Arrays.asList(words));
    }

    @Override
    public List<String> getAll() {
        return this.words;
    }
}

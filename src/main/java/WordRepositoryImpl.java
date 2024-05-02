import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordRepositoryImpl implements WordRepository {
    private final ArrayList<String> words;
    public WordRepositoryImpl(String... words) {
        this.words = new ArrayList<>(Arrays.asList(words));}


    @Override
    public List<String> getAll() {
        return null;
    }
}

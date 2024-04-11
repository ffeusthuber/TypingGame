import java.util.ArrayList;
import java.util.Arrays;

public class WordRepositoryImpl implements WordRepository {
    private final ArrayList<String> words;
    public WordRepositoryImpl(String... words) {
        this.words = new ArrayList<>(Arrays.asList(words));}

    @Override
    public String getWordByIndex(int wordIndex) {
        if(wordIndex >= getNumberOfWords()){
            throw new IndexOutOfBoundsException("Repository contains " + getNumberOfWords() + " word(s). Index of " + wordIndex + " is out of range.");
        }
        return words.get(wordIndex);
    }

    @Override
    public int getNumberOfWords() {
        return words.size();
    }
}

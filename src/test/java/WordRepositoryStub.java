import java.util.ArrayList;
import java.util.Arrays;

public class WordRepositoryStub implements WordRepository {
    private final ArrayList<Word> words = new ArrayList<>(Arrays.asList(
            new Word("Apple"),
            new Word("Banana")));

    @Override
    public Word getWordByIndex(int wordIndex) {
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

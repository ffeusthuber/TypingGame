package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameField {

    private final List<Word> words = new ArrayList<>();
    private final List<Position> spawnPoints;

    private final int height;

    public GameField (int height, List<Position> spawnPoints){
        this.height = height;
        this.spawnPoints = spawnPoints;
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public void removeWord(Word word) {
        words.remove(word);
    }

    public List<Word> getWords() {
        return Collections.unmodifiableList(words);
    }

    public List<Position> getSpawnPoints() {
        return Collections.unmodifiableList(spawnPoints);
    }

    public int getHeight() {
        return this.height;
    }

}

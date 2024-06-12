package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameField {

    private final List<Word> words = new ArrayList<>();
    private final List<Position> spawnPoints = new ArrayList<>();

    public void addWord(Word word) {
        words.add(word);
    }

    public void removeWord(Word word) {
        words.remove(word);
    }

    public List<Word> getWords() {
        return Collections.unmodifiableList(words);
    }

    public void addSpawnPoint(Position position) {
        spawnPoints.add(position);
    }

    public List<Position> getSpawnPoints() {
        return Collections.unmodifiableList(spawnPoints);
    }

    public void moveWords(int stepSize) {
        words.forEach(word -> word.moveY(stepSize));
    }
}

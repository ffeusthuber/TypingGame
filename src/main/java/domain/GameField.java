package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//gamefield
//has list of all displayed words
//words spawned by wordspawner must be added to gamefield
//wordspawner must have reference of gamefield

//spawnpoints
//gameoverzone


// add Word to field (wordSpawner adds)
// getWords on field (wordTargeter needs)
// remove Word from field

//setup gamefield:
// set spawnpoints on start
// get spawnpoints as list (wordSpawner takes Spawnpoints from here)
// set gameoverfield

public class GameField {

    private final List<Word> words = new ArrayList<>();
    public void addWord(Word word) {
        words.add(word);
    }

    public List<Word> getWords() {
        return Collections.unmodifiableList(words);
    }

    public void removeWord(Word word) {
        words.remove(word);
    }
}

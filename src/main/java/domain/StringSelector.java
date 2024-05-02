package domain;

import adapter.in.WordRepository;

import java.util.List;

public class StringSelector {
    private final RandomNumbers randomNumberGenerator;
    private final List<String> wordList;

    public StringSelector(WordRepository wordRepository, RandomNumbers randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;

        wordList = wordRepository.getAll();
    }

    public String random() {
        return wordList.get(
                randomNumberGenerator.nextInt(wordList.size()-1));
    }

    List<String> getWordList() {
        return this.wordList;
    }
}

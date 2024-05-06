package domain;

import adapter.in.WordRepository;
import util.RandomNumbers;

import java.util.List;

public class StringSelectorImpl implements StringSelector {
    private final RandomNumbers randomNumberGenerator;
    private final List<String> wordList;

    public StringSelectorImpl(WordRepository wordRepository, RandomNumbers randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;

        wordList = wordRepository.getAll();
    }

    @Override
    public String random() {
        return wordList.get(
                randomNumberGenerator.nextInt(wordList.size()-1));
    }

    List<String> getWordList() {
        return this.wordList;
    }
}

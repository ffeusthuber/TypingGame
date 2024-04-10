public class WordRepositoryStub implements WordRepository {
    @Override
    public Word getWordByNumber(int wordNumber) {
        return new Word("Apple");
    }
}

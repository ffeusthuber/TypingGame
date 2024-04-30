public class TypingGame {
    private final WordSpawner wordSpawner;
    private int playerLives;
    public TypingGame(WordSpawner wordSpawner){
        this.playerLives = 3;
        this.wordSpawner = wordSpawner;
    }
    public void start() {
        wordSpawner.spawn();
    }

    public int getPlayerLives() {
        return this.playerLives;
    }
}

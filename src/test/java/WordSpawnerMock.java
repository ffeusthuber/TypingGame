public class WordSpawnerMock implements WordSpawner {
    boolean spawnWasCalled;

    @Override
    public void spawn() {
        spawnWasCalled = true;
    }

    boolean spawnWasCalled() {
        return this.spawnWasCalled;
    }

}

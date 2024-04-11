public class RandomNumbersStub implements RandomNumbers {

    int upperLimit;

    @Override
    public int nextInt() {
        return 1;
    }

    @Override
    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public int getUpperLimit() {
        return this.upperLimit;
    }
}


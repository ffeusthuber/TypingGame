public class RandomNumbersStub implements RandomNumbers {

    final int upperLimit;

    public RandomNumbersStub(int upperLimit){
        this.upperLimit = upperLimit;
    }

    @Override
    public int nextInt() {
        return 1;
    }

    public int getUpperLimit() {
        return this.upperLimit;
    }
}


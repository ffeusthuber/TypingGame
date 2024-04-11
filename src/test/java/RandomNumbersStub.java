public class RandomNumbersStub implements RandomNumbers {
    private int upperLimit;
    @Override
    public int nextInt(int upperLimit) {
        this.upperLimit = upperLimit;
        return 1;
    }

    public int getUpperLimit(){
        return upperLimit;
    }
}


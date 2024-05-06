package util;

public class RandomNumbersStub implements RandomNumbers {
    private final int numberToReturn;
    private int upperLimit;

    public RandomNumbersStub(int numberToReturn){
        this.numberToReturn = numberToReturn;
    }
    @Override
    public int nextInt(int upperLimit) {
        this.upperLimit = upperLimit;
        return this.numberToReturn;
    }

    public int getUpperLimit(){
        return upperLimit;
    }
}


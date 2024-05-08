package util;

import java.util.Random;

public class RandomNumberGenerator implements RandomNumbers{

    private final Random rand;

    public RandomNumberGenerator(){
        rand = new Random();
    }
    @Override
    public int nextInt(int upperLimit) {
        return rand.nextInt(upperLimit);
    }
}

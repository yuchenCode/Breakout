package utils;

import java.util.Random;

public class RandomDoubleCreator {

    // create random double number between min and max
    public static double randomDouble(double min, double max) {
        return min + ((max - min) * new Random().nextDouble());
    }
}

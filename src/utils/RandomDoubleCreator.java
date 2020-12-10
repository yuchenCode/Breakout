package utils;

import java.util.Random;

public class RandomDoubleCreator {

    public static double randomDouble(double min, double max) {
        return min + ((max - min) * new Random().nextDouble());
    }
}

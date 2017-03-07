package com.darwindev;
import java.util.Date;
import java.util.Random;

public class RandomData {
    public static int[] generate1d(int nbVals, int min, int max) {
        int[] res = new int[nbVals];
        Random generator = new Random(new Date().getTime());
        for (int i = 0; i != nbVals; ++i) {
            res[i] = (int)((Math.abs(generator.nextLong()) % ((long)max - min)) + min);
        }
        return res;
    }
}

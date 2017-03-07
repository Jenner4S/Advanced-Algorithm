package com.darwindev.timemeasurement;

/**
 * Algorithm - Swap the value of two variables.
 * Time complexity: O(1)
 * Created by Zheng on 07/03/2017.
 */
public class Swap {
    public static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}

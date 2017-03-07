package com.darwindev.algo;

import com.darwindev.RandomData;

/**
 * Algorithmic - Binary search
 * Time complexity: O(lgn)
 * Created by Zheng on 07/03/2017.
 */
public class BinarySearch implements Algo {
    // index of first element >= v
    private static int lowerBound(int[] data, int v) {
        return lowerBound(data, v, 0, data.length);
    }

    private static int lowerBound(int[] data, int v, int begin, int end) {
        if (begin == end) {
            return begin;
        }
        int m = (begin + end) / 2;
        return data[m] < v ? lowerBound(data, v, m + 1, end) : lowerBound(data, v, begin, m);
    }

    public static int binarySearch(int[] inputData, int val) {
        for (int i = 0; i != inputData.length; ++i) {
            inputData[i] = 2 * i;
        }
        return lowerBound(inputData, val);
    }

    @Override
    public int rangeBegin() {
        return 5000000;
    }

    @Override
    public int rangeEnd() {
        return 100000000;
    }

    @Override
    public int rangeStep() {
        return 1000000;
    }

    @Override
    public void perform(int[] tab) {
        int val = RandomData.generate1d(1, 0, tab.length - 1)[0];
        binarySearch(tab, val);
    }
}

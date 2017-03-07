package com.darwindev;

/**
 * Algorithm - Bubble sort
 * Time complexity: O(n^2)
 * Created by Zheng on 07/03/2017.
 */
public class BubbleSort implements AlgoSort {

    public static void bubbleSort(int[] inputData) {
        if (inputData.length < 2) return;
        boolean hadToSwap;
        do {
            hadToSwap = false;
            for (int i = 0; i != inputData.length - 1; ++i) {
                if (inputData[i] > inputData[i + 1]) {
                    Swap.swap(inputData, i, i + 1);
                    hadToSwap = true;
                }
            }
        } while (hadToSwap);
    }

    @Override
    public void sort(int[] tab) {
        BubbleSort.bubbleSort(tab);
    }

    @Override
    public int rangeBegin() {
        return 500;
    }

    @Override
    public int rangeEnd() {
        return 10000;
    }

    @Override
    public int rangeStep() {
        return 100;
    }

    @Override
    public void perform(int[] tab) {
        sort(tab);
    }

}

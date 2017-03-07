package com.darwindev.algo;

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

    public void sort(int[] tab) {
        BubbleSort.bubbleSort(tab);
    }

    public void perform(int[] tab) {
        sort(tab);
    }

}

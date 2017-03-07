package com.darwindev.algo;

/**
 * Algorithm - Selection sort
 * Complexity: O(n^2)
 * Created by Zheng on 07/03/2017.
 */
public class SelectionSort implements AlgoSort {

    public static void selectionSort(int[] inputData) {
        if (inputData.length < 2) return;
        for (int i = 0; i < inputData.length; i++) {
            Swap.swap(inputData, i, FindMinimum.findMinimumIndex(inputData, i, inputData.length));
        }
    }

    @Override
    public void sort(int[] tab) {
        SelectionSort.selectionSort(tab);
    }

    @Override
    public int rangeBegin() {
        return 5000;
    }

    @Override
    public int rangeEnd() {
        return 100000;
    }

    @Override
    public int rangeStep() {
        return 1000;
    }

    @Override
    public void perform(int[] tab) {
        sort(tab);
    }

}

package com.darwindev.algo;

/**
 * Algorithm - Quick sort
 * Time complexity: O(nlgn)
 * Created by Zheng on 07/03/2017.
 */
public class QuickSort implements AlgoSort {

    private static int partition(int[] inputData, int begin, int end, int pivotIdx) {
        Swap.swap(inputData, pivotIdx, --end);
        pivotIdx = end;
        int pivot = inputData[pivotIdx];
        // invariant is that everything before begin is known to be < pivot
        // and everything after end is known to be >= pivot
        while (begin != end) {
            if (inputData[begin] >= pivot) {
                Swap.swap(inputData, begin, --end);
            } else {
                ++begin;
            }
        }
        Swap.swap(inputData, pivotIdx, begin);
        return begin;
    }

    public static void quickSort(int[] inputData) {
        quickSort(inputData, 0, inputData.length);
    }

    public static void quickSort(int[] inputData, int begin, int end) {
        if ((end - begin) < 2) {
            return;
        }
        int m = partition(inputData, begin, end, (end + begin) / 2);
        quickSort(inputData, begin, m);
        quickSort(inputData, m + 1, end); // +1 for convergence
    }

    public void sort(int[] tab) {
        QuickSort.quickSort(tab);
    }

    public void perform(int[] tab) {
        sort(tab);
    }

}

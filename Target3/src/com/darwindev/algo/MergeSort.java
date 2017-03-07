package com.darwindev.algo;

/**
 * Algorithm - Merge sort
 * Time complexity: O(nlgn)
 * Created by Zheng on 07/03/2017.
 */
public class MergeSort implements AlgoSort {

    private static void mergeSorted(int inputData[], int begin, int middle, int end) {
        int[] tmp = new int[middle - begin];
        System.arraycopy(inputData, begin, tmp, 0, tmp.length);
        int i = 0, j = middle, dest = begin;
        while ((i < tmp.length) && (j < end)) {
            inputData[dest++] = (tmp[i] < inputData[j]) ? tmp[i++] : inputData[j++];
        }
        while (i < tmp.length) {
            inputData[dest++] = tmp[i++];
        }
    }

    public static void mergeSort(int[] inputData) {
        mergeSort(inputData, 0, inputData.length);
    }

    public static void mergeSort(int[] inputData, int begin, int end) {
        if ((end - begin) < 2) {
            return;
        }
        int middle = (end + begin) / 2;
        mergeSort(inputData, begin, middle);
        mergeSort(inputData, middle, end);
        mergeSorted(inputData, begin, middle, end);
    }

    public void sort(int[] tab) {
        MergeSort.mergeSort(tab);
    }

    public void perform(int[] tab) {
        sort(tab);
    }

 }

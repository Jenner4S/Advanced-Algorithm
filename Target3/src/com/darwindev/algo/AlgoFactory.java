package com.darwindev.algo;

/**
 * Algorithm Factory - singleton
 * Created by Zheng on 07/03/2017.
 */
public class AlgoFactory {
    public static Algo generateAlgo(AlgoName algoName) {
        Algo algoInstance;
        switch (algoName) {
            case AlgoFindMinimum:
                algoInstance = new FindMinimum();
                break;
            case AlgoSelectionSort:
                algoInstance = new SelectionSort();
                break;
            case AlgoBubbleSort:
                algoInstance = new BubbleSort();
                break;
            case AlgoMergeSort:
                algoInstance = new MergeSort();
                break;
            case AlgoQuickSort:
                algoInstance = new QuickSort();
                break;
            case AlgoBinarySearch:
                algoInstance = new BinarySearch();
                break;
            default:
                algoInstance = new FindMinimum();
                break;
        }
        return algoInstance;
    }
}

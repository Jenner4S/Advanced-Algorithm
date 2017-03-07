package com.darwindev.algo;

/**
 * Base interface
 * Created by Zheng on 07/03/2017.
 */
public interface Algo {
    public int rangeBegin();
    public int rangeEnd();
    public int rangeStep();

    public void perform(int[] tab);
}

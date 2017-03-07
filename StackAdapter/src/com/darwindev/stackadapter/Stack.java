package com.darwindev.stackadapter;

/**
 * Simple stack interface
 * Created by Zheng on 07/03/2017.
 */
public interface Stack<T> {
    void push(T o);
    T pop();
    T top();
}

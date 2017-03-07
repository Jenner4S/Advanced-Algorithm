package com.darwindev.stackadapter;

/**
 * Stack Adapter
 * Created by Zheng on 07/03/2017.
 */
public class StackAdapter<T> extends DList<T> implements Stack<T> {

    @Override
    public void push(T o) {
        insertHead(o);
    }

    @Override
    public T pop() {
        T head = getHead();
        removeHead();
        return head;
    }

    @Override
    public T top() {
        return getHead();
    }

}
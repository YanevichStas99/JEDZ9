package com.company;

public interface MyMap<T> {
    void clear();
    boolean containsKey(int key);
    boolean containsValue(int value);
    T get(int key);
    T put(T key, T value);
    T remove(T key);
    int size();

}

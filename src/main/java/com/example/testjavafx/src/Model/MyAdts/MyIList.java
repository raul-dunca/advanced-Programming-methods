package com.example.testjavafx.src.Model.MyAdts;

public interface MyIList<T> {
    void append(T s);
    int size();

    T get_index(int i);

    String tostr();

}

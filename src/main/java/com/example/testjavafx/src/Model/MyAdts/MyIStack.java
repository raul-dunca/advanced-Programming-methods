package com.example.testjavafx.src.Model.MyAdts;

public interface MyIStack<T> {


    T pop();
    void push(T v);
    String tostr();
    boolean isempty();

    T top();
}

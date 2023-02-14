package com.example.testjavafx.src.Model.MyAdts;

import com.example.testjavafx.src.Exception.*;

import java.util.Collection;

public interface MyIDictionary<K,V> {

    boolean isDefined(K a);

    V lookup(K a) throws MyException;

    void update(K a, V val);

    void add(K a, V val);

    void delete(K a);
    String tostr();

    Collection<K> getvkeys();
    Collection<V> getvalues();
}

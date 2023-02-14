package com.example.testjavafx.src.Model.MyAdts;

import kotlin.Pair;

import java.util.Collection;
import java.util.Map;

public interface MyIProcTable<K,V,T> {
    boolean isOccupied(K  a);

    Pair<V,T> lookup(K a);

    void update(K a, Pair<V,T> val);

    void add(K a,Pair<V,T> val);

    void delete(K a);
    String tostr();

    int getFreeloc();

    Collection<K> getvkeys();

    Map<K,Pair<V,T>> retall();

    Collection<Pair<V,T>> getvalues();


}

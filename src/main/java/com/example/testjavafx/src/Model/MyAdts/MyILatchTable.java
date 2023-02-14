package com.example.testjavafx.src.Model.MyAdts;

import java.util.Collection;
import java.util.Map;

public interface MyILatchTable<K,V> {
    boolean isOccupied(K  a);

    V lookup(K a);

    void update(K a, V val);

    void add(K a,V val);

    void delete(K a);
    String tostr();

    int getFreeloc();


    Collection<K> getvkeys();
    Map<K,V> retall();

    Collection<V> getvalues();

}

package com.example.testjavafx.src.Model.MyAdts;

import com.example.testjavafx.src.Model.Value.Value;

import java.util.Collection;
import java.util.Map;

public interface MyIHeap<K,V> {
    boolean isOccupied(K  a);

    V lookup(K a);

    void update(K a, V val);

    void add(K a,V val);

    void delete(K a);
    String tostr();

    int getFreeloc();

    void setContent(Map<K, V> unsafeGarbageCollector);

    Collection<K> getvkeys();
    Map<K,V> retall();

    Collection<V> getvalues();

    Iterable<Map.Entry<K, V>> getAll();
}

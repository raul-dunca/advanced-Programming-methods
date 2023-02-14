package com.example.testjavafx.src.Model.MyAdts;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<K,V> implements  MyIHeap<K,V> {
    int freeloc=1;
    HashMap<K, V> hashMap=new HashMap<K, V>();
    @Override
    public boolean isOccupied(K  a) {
        for (K i : hashMap.keySet())
        {
            if(i==a)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public V lookup(K a) {
        return hashMap.get(a);
    }

    public int getFreeloc()
    {
        return freeloc;
    }

    @Override
    public void setContent(Map<K, V> unsafeGarbageCollector) {
        hashMap.clear();
        hashMap= new HashMap<K,V> (unsafeGarbageCollector);
    }

    @Override
    public Map<K, V> retall() {
        return hashMap;
    }

    @Override
    public Collection<V> getvalues() {
        return  hashMap.values();
    }
    @Override
    public Collection<K> getvkeys() {
        return hashMap.keySet();
    }
    @Override
    public void update(K  a, V val) {
        hashMap.replace(a,hashMap.get(a),val);
    }

    @Override
    public void add(K a,V val) {
        hashMap.put(a,val);
        freeloc++;
    }

    @Override
    public void delete(K a) {
        hashMap.remove(a);
    }

    @Override
    public String tostr() {
        return hashMap.toString();
    }

    public Iterable<Map.Entry<K, V>> getAll() {
        return hashMap.entrySet();
    }
}

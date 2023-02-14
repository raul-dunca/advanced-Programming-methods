package com.example.testjavafx.src.Model.MyAdts;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyLockTable<K,V> implements MyILockTable<K,V>{

    HashMap<K, V> hashMap=new HashMap<K, V>();
    int freeloc=1;

    public synchronized boolean isOccupied(K  a) {
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
    public synchronized V lookup(K a) {
        return hashMap.get(a);
    }


   /* @Override
    public void setContent(Map<K, V> unsafeGarbageCollector) {
        hashMap.clear();
        hashMap= new HashMap<K,V> (unsafeGarbageCollector);
    }*/

    @Override
    public synchronized Map<K, V> retall() {
        return hashMap;
    }

    @Override
    public synchronized Collection<V> getvalues() {
        return  hashMap.values();
    }
    @Override
    public synchronized Collection<K> getvkeys() {
        return hashMap.keySet();
    }
    @Override
    public synchronized void update(K  a, V val) {
        hashMap.replace(a,hashMap.get(a),val);
    }

    @Override
    public synchronized void add(K a,V val) {
        hashMap.put(a,val);
        freeloc++;
    }

    @Override
    public synchronized void delete(K a) {
        hashMap.remove(a);
    }

    @Override
    public String tostr() {
        return hashMap.toString();
    }

    @Override
    public int getFreeloc() {
        return freeloc;
    }
}

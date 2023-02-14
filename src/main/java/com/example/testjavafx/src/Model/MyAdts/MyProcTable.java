package com.example.testjavafx.src.Model.MyAdts;

import kotlin.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyProcTable<K,V,T> implements MyIProcTable<K,V,T> {
    int freeloc=1;
    HashMap<K, Pair<V,T>> hashMap=new HashMap<K, Pair<V,T>>();

    @Override
    public boolean isOccupied(K a) {
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
    public Pair<V,T> lookup(K v) {
        return hashMap.get(v);
    }

    @Override
    public void update(K a, Pair<V, T> val) {
        hashMap.replace(a,hashMap.get(a),val);
    }

    @Override
    public void add(K a, Pair<V, T> val) {
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

    @Override
    public int getFreeloc() {
        return freeloc;
    }

    @Override
    public Collection<K> getvkeys() {
        return hashMap.keySet();
    }

    @Override
    public Map<K, Pair<V, T>> retall() {
        return hashMap;
    }


    @Override
    public Collection<Pair<V,T>> getvalues() {
        return  hashMap.values();
    }
}

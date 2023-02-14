package com.example.testjavafx.src.Model.MyAdts;

import com.example.testjavafx.src.Exception.*;

import java.util.Collection;
import java.util.HashMap;

public class MyDictionary<K,T> implements MyIDictionary<K,T>{
    HashMap<K,T> hashMap=new HashMap<K, T>();

    @Override
    public boolean isDefined(K a) {
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
    public T lookup(K a) throws MyException {
        T check= hashMap.get(a);
        if (check==null)
            throw new MyException("Variable: "+a.toString() +" not in the table!");
        else
            return check;
    }
    @Override
    public Collection<T> getvalues()
    {
        return hashMap.values();
    }
    @Override
    public void update(K a, T val) {
            hashMap.replace(a,hashMap.get(a),val);
    }

    @Override
    public void add(K a, T val) {
        hashMap.put(a,val);
    }

    @Override
    public void delete(K a) {
        hashMap.remove(a);
    }

    public String tostr()
    {
        return hashMap.toString();
    }

    @Override
    public Collection<K> getvkeys() {
        return hashMap.keySet();
    }

}

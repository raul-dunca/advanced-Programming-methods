package com.example.testjavafx.src.Model.MyAdts;

import java.util.Vector;

public class MyList<T> implements MyIList<T> {
    Vector<T> v=new Vector<T>();
    @Override
    public void append(T s) {
        v.add(s);
    }
    @Override
    public int size()
    {
        return v.size();
    }

    @Override
    public T get_index(int i) {
        return v.get(i);
    }

    @Override
    public String tostr() {
        String s=new String();
        for(int i=0;i<v.size();i++)
        {

            s+=v.get(i).toString();
            s+="\n";
        }
        return s;
    }
}

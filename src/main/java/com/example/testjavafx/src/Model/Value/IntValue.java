package com.example.testjavafx.src.Model.Value;

import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.Type;

public class IntValue implements Value {
    int val;
    public IntValue(int v)
    {
        val=v;
    }

    public int getVal()
    {
        return  val;
    }
    public String toString()
    {
        return "Int value: "+ val;
    }

    @Override
    public boolean equals(Object a) {
        if (a instanceof IntValue)
        {

            if(((IntValue) a).getVal()==val)
                return true;
            else
                return false;
        }
        else
            return false;

    }
    @Override
    public Type getType()
    {
        return new IntType();
    }

    @Override
    public Value deepcopy() {
        return new IntValue(val);
    }


}

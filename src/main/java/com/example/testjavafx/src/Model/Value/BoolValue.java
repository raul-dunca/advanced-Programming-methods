package com.example.testjavafx.src.Model.Value;

import com.example.testjavafx.src.Model.Type.BoolType;
import com.example.testjavafx.src.Model.Type.Type;

public class BoolValue implements Value {
    boolean val;
    public BoolValue(boolean b)
    {
        val=b;
    }
    public boolean getVal()
    {
        return val;
    }
    public String toString()
    {
        return "Bool value: "+ val;
    }

    @Override
    public Type getType()
    {
        return new BoolType();
    }

    @Override
    public Value deepcopy() {
        return new BoolValue(val);
    }
    @Override
    public boolean equals(Object a) {
        if (a instanceof BoolValue)
        {

            if(((BoolValue) a).getVal()==val)
                return true;
            else
                return false;
        }
        else
            return false;

    }

}

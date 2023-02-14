package com.example.testjavafx.src.Model.Type;

import com.example.testjavafx.src.Model.Value.BoolValue;
import com.example.testjavafx.src.Model.Value.Value;

public class BoolType implements Type {
    boolean val;
    @Override
    public boolean equals(Object another)
    {
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public Value defaultvalue() {
        return new BoolValue(false);
    }

    @Override
    public Type deepcopy() {
        return new BoolType();
    }

    public String toString()
    {
        return "bool";
    }
}

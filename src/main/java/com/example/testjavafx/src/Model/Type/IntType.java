package com.example.testjavafx.src.Model.Type;

import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.Value;

public class IntType implements Type {
    @Override
    public boolean equals(Object another)
    {
        if (another instanceof IntType)
            return true;
        else
            return false;
    }
    @Override
    public Value defaultvalue() {
        return new IntValue(0);
    }

    @Override
    public Type deepcopy() {
        return new IntType();
    }

    public String toString()
    {
        return "int";
    }
}

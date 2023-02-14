package com.example.testjavafx.src.Model.Type;

import com.example.testjavafx.src.Model.Value.StringValue;
import com.example.testjavafx.src.Model.Value.Value;

public class StringType implements Type{

    @Override
    public boolean equals(Object another)
    {
        if (another instanceof StringType)
            return true;
        else
            return false;
    }
    @Override
    public Value defaultvalue() {
        return new StringValue("");
    }

    @Override
    public Type deepcopy() {
        return new StringType();
    }
    public String toString()
    {
        return "string";
    }
}

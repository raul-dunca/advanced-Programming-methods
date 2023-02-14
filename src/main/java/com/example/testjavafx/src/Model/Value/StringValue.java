package com.example.testjavafx.src.Model.Value;

import com.example.testjavafx.src.Model.Type.StringType;
import com.example.testjavafx.src.Model.Type.Type;

import java.util.Objects;

public class StringValue implements Value{
    String s;

    public String toString()
    {
        return "String value: "+ s;
    }
    public StringValue(String st)
    {
        s=st;
    }
    public String getVal()
    {
        return s;
    }
    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepcopy() {
        return new StringValue(s);
    }

    @Override
    public boolean equals(Object a) {
        if (a instanceof StringValue)
        {
            if(Objects.equals(((StringValue) a).getVal(), s))
                return true;
            else
                return false;
        }
        else
            return false;

    }
}

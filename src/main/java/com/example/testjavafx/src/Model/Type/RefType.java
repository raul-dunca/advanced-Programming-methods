package com.example.testjavafx.src.Model.Type;

import com.example.testjavafx.src.Model.Value.RefValue;
import com.example.testjavafx.src.Model.Value.Value;

public class RefType implements Type{
    Type inner;
    public RefType(Type in)
    {
        inner=in;
    }
    @Override
    public Value defaultvalue() {
        return new RefValue(0,inner);
    }

    @Override
    public Type deepcopy() {
        return new RefType(inner.deepcopy());
    }
    public Type getInner()
    {
        return inner;
    }
    @Override
    public boolean equals(Object another)
    {
        if (another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else
            return false;
    }

    public String toString()
    {
        return "Ref("+inner.toString()+")";
    }
}

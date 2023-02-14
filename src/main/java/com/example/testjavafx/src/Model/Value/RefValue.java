package com.example.testjavafx.src.Model.Value;

import com.example.testjavafx.src.Model.Type.RefType;
import com.example.testjavafx.src.Model.Type.Type;

public class RefValue implements Value{
    int addres;
    Type locationType;
    public RefValue(int a, Type t)
    {
        addres=a;
        locationType=t;
    }
    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    public int getAddres() {
        return addres;
    }
/*    @Override
    public boolean equals(Object a) {
        if (a instanceof RefValue)
        {

            if(((IntValue) a).getVal()==val)
                return true;
            else
                return false;
        }
        else
            return false;

    }*/
    public String toString()
    {
        return "("+ addres+", "+locationType.toString()+")";
    }

    @Override
    public Value deepcopy() {
        return new RefValue(addres,locationType);
    }
}

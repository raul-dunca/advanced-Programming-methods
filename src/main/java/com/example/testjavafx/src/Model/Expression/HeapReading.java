package com.example.testjavafx.src.Model.Expression;

import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.Type.RefType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.RefValue;
import com.example.testjavafx.src.Model.Value.Value;

public class HeapReading implements Exp{
    Exp e;
    public HeapReading(Exp ex)
    {
        e=ex;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer,Value> hp) throws MyException, DvisionByZeroExc {
        Value v=e.eval(tbl,hp);
        if (v.getType() instanceof RefType)
        {
            RefValue v2=(RefValue) v;
            if (hp.isOccupied(v2.getAddres()))
            {
                return hp.lookup(v2.getAddres());
            }
            else
            {
                throw new MyException("Address "+v2.getAddres()+" is invalid!");
            }
        }
        else
        {
            throw new MyException("The expression need to be of type RefType!");
        }
    }

    @Override
    public Exp deepcopy() {
        return new HeapReading(e.deepcopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1;
        t1=e.typecheck(typeEnv);
        if (t1 instanceof RefType)
        {
            RefType reft= (RefType) t1;
            return reft.getInner();
        }
        else
        {
            throw new MyException("the rH argument is not a Ref Type");
        }
    }

    public String toString() {
        return "HeapReading: "+e;
    }
}

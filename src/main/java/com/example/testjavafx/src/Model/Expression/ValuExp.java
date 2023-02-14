package com.example.testjavafx.src.Model.Expression;

import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;
public class ValuExp implements Exp {
    Value e;
    public ValuExp(Value er)
    {
        e=er;
    }
    public String toString()
    {
        return "Value exp: "+e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        return e;
    }

    @Override
    public Exp deepcopy() {
        return new ValuExp(e.deepcopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }
}

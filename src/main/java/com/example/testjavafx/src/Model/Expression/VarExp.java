package com.example.testjavafx.src.Model.Expression;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;

public class VarExp implements Exp {
    String id;
    public VarExp(String i)
    {
        id=i;
    }
    public String toString() {
        return "VarEval exp : " + id;
    }
    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException
    {return tbl.lookup(id);}

    @Override
    public Exp deepcopy() {
        return new VarExp(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }
}

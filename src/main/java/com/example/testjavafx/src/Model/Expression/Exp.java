package com.example.testjavafx.src.Model.Expression;

import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;
public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException, DvisionByZeroExc;
    public Exp deepcopy();
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;

}

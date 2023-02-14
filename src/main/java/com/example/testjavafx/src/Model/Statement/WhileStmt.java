package com.example.testjavafx.src.Model.Statement;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.MyDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.BoolType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.BoolValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;

public class WhileStmt implements IStmt{
    Exp e;
    IStmt s;
    public WhileStmt(Exp ex, IStmt st)
    {
        e=ex;
        s=st;
    }
    public String toString()
    {
        return "While("+ e+"): " +s;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        Value v=e.eval(tbl,hp);
        if ( (v.getType()).equals(new BoolType()) )
        {
            BoolValue v2=(BoolValue)v;
            if(v2.getVal())
            {
                stk.push(this);
                stk.push(s);
               // stk.push(this);
            }
        }
        else
        {
            throw new MyException("The condition need to be boolean!!");
        }
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new WhileStmt(e.deepcopy(),s.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeexp=e.typecheck(typeEnv);
        MyIDictionary<String,Type> newtypeEnv1=new MyDictionary<String,Type>();
        if (typeexp.equals(new BoolType()))
        {
            for(String k : typeEnv.getvkeys())
            {
                newtypeEnv1.add(k,typeEnv.lookup(k));
            }
            s.typecheck(newtypeEnv1);
            return typeEnv;
        }
        else
            throw new MyException("The WHILE condition is not of type boolean!");
    }
}

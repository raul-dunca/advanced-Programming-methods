package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.*;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

public class CallProcStmt implements IStmt{
    String name;
    ArrayList<Exp> params;
    public CallProcStmt(String n, ArrayList<Exp> p)
    {
        name=n;
        params=p;
    }
    public String toString()
    {
        return "call "+ name+" ("+params+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIProcTable<String,ArrayList<String>,IStmt> prcTbl=state.getProcTable();
        ArrayList<String> p=new ArrayList<String>();
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        MyIDictionary<String, Value> newSymTBl=new MyDictionary<String, Value>();
        MyIStack<MyIDictionary<String,Value>> SymStack=state.getAllSymTables();
        MyIStack<IStmt> stk=state.getStk();
        if (prcTbl.isOccupied(name))
        {
            p=prcTbl.lookup(name).getFirst();
            for (int i=0;i<params.size();i++)
            {
                Value v=params.get(i).eval(tbl,hp);
                newSymTBl.add(p.get(i),v);

            }

            SymStack.push(newSymTBl);
            stk.push(new ReturnStmt());
            stk.push(prcTbl.lookup(name).getSecond());

        }
        else
            throw new MyException("Function "+name+"not defined!");

        return null;
    }

    @Override
    public IStmt deepcopy() {
        ArrayList<Exp> newparams=new ArrayList<Exp>();
        for (int i=0;i<params.size();i++)
        {
            newparams.add(params.get(i).deepcopy());
        }
        return new CallProcStmt(name,newparams);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}

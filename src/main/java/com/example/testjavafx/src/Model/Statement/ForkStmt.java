package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.*;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

public class ForkStmt implements IStmt{

    IStmt stmt;

    public ForkStmt(IStmt s)
    {
        stmt=s;
    }
    public String toString()
    {
        return "FORK ( "+ stmt+" )";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {


        MyIStack<IStmt> newstk=new MyStack<IStmt>();
        MyIStack<MyIDictionary<String, Value>> newSTKsymtbl=new MyStack<MyIDictionary<String, Value>>();
        MyIDictionary<String, Value> current=new MyDictionary<String,Value>();
        MyIDictionary<String, Value> newSymTbl=new MyDictionary<String,Value>();
        MyIStack<MyIDictionary<String, Value>> flippedStack=new MyStack<MyIDictionary<String, Value>>();
        MyIProcTable<String, ArrayList<String>,IStmt> prcTbl=state.getProcTable();
        while(!state.getAllSymTables().isempty()) {
            current=state.getAllSymTables().pop();
            flippedStack.push(current);

        }
        while(!flippedStack.isempty()) {
            current=flippedStack.pop();
            for (String k : current.getvkeys()) {
                newSymTbl.add(k, current.lookup(k));
            }
            newSTKsymtbl.push(newSymTbl);
            state.getAllSymTables().push(current);
        }

        PrgState newprg=new PrgState(newstk,newSTKsymtbl,state.getList(),state.getFileTable(),state.getHeap(),state.getLatchTable(),state.getLockTable(),prcTbl,stmt);
        return newprg;

    }

    @Override
    public IStmt deepcopy() {
        return new ForkStmt(stmt.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        MyIDictionary<String,Type> newtypeEnv1=new MyDictionary<String,Type>();
        for(String k : typeEnv.getvkeys())
        {
            newtypeEnv1.add(k,typeEnv.lookup(k));
        }
        stmt.typecheck(newtypeEnv1);
        return typeEnv;
    }
}

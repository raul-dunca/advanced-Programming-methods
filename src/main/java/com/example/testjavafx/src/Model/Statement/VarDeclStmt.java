package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;

public class VarDeclStmt implements IStmt {
    String name;
    Type tp;
    public VarDeclStmt(String n, Type t)
    {
        name=n;
        tp=t;
    }
    public String toString()
    {

        return "ValDeclaration: " + tp+" "+name;

    }
    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIStack<IStmt> stk=state.getStk();

        MyIDictionary<String, Value> symTbl= state.getSymTable();
        if(symTbl.isDefined(name))
        {
            throw new MyException("Variable "+name+"already defined");
        }
        else
        {
            symTbl.add(name,tp.defaultvalue());
        }
        //stk.pop();
        return null;


    }

    @Override
    public IStmt deepcopy() {
        return new VarDeclStmt(name,tp.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(name,tp);
        return typeEnv;
    }

}

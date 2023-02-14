package com.example.testjavafx.src.Model.Statement;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.Type;

public class CompStmt implements IStmt
{
    IStmt first;
    IStmt second;
    public CompStmt(IStmt s, IStmt t)
    {
        first=s;
        second=t;
    }
    public String toString()
    {
        return "Compound Stmt ( "+first.toString()+", "+second.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIStack<IStmt> stk=state.getStk();
        stk.push(second);
        stk.push(first);

        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new CompStmt(first.deepcopy(),second.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        MyIDictionary<String,Type> typeEnv1=first.typecheck(typeEnv);
        MyIDictionary<String,Type> typeEnv2=second.typecheck(typeEnv1);
        return typeEnv2;
    }
}
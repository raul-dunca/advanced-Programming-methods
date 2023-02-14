package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;

public class ReturnStmt implements IStmt{
    ReturnStmt()
    {

    }
    public String toString()
    {
        return "return";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {

        MyIStack<MyIDictionary<String, Value>> SymStack=state.getAllSymTables();
        SymStack.pop();
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new ReturnStmt();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}

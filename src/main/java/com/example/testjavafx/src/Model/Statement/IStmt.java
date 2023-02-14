package com.example.testjavafx.src.Model.Statement;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.Type;

import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException;
    public IStmt deepcopy();

    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}

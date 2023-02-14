package com.example.testjavafx.src.Model.Statement;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.StringType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.StringValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStmt implements IStmt{
    Exp exp;
    public CloseFileStmt(Exp e)
    {
        exp=e;
    }
    public String toString()
    {
        return "CloseFile: "+exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> filetbl=state.getFileTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        Value v= exp.eval(tbl,hp);
        if(v.getType().equals(new StringType()))
        {
            if (filetbl.isDefined((StringValue) v)) {
                try{
                BufferedReader br = filetbl.lookup((StringValue) v);
                br.close();
                filetbl.delete((StringValue) v);
                }
                catch(IOException err)
                {
                    System.err.println("Eroare"+err);
                }
            }
            else
            {
                throw new MyException(v+" not defined in filetable!");
            }
        }
        else
        {
            throw new MyException("The exp must be StringType!");
        }
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new CloseFileStmt(exp.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type Typeexp=exp.typecheck(typeEnv);
        if (Typeexp.equals(new StringType()))
        {
            return typeEnv;
        }
        else
            throw new MyException("CloseStmt: exp needs to be of type string!");
    }
}

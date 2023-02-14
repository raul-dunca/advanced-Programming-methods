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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStmt implements IStmt{
    Exp exp;
    public OpenFileStmt(Exp e)
    {
        exp=e;
    }
    public String toString()
    {
        return "OpenFile: "+exp.toString();
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, FileNotFoundException {

        //MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> filetbl=state.getFileTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        Value v=exp.eval(tbl,hp);
        if(v.getType().equals(new StringType()))
        {
            if((!filetbl.isDefined((StringValue) v)))
            {
                BufferedReader br=null;
                try {
                    br = new BufferedReader(new FileReader(((StringValue) v).getVal()));
                    filetbl.add((StringValue) v,br);
                }catch (FileNotFoundException e) {
                    System.err.println("Eroare scriere PW " + e);
                }

            }
            else
            {
                throw new MyException("File already in the FileTable!");
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
        return new OpenFileStmt(exp.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type Typeexp=exp.typecheck(typeEnv);
        if (Typeexp.equals(new StringType()))
        {
            return typeEnv;
        }
        else
            throw new MyException("OpenStmt: exp needs to be of type string!");
    }
}

package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.StringType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.StringValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt{
    Exp exp;
    String var_name;
    public ReadFileStmt(Exp e, String v)
    {
        exp=e;
        var_name=v;
    }
    public String toString()
    {
        return "ReadFile: "+exp.toString()+" in: "+var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> filetbl=state.getFileTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        if(tbl.isDefined(var_name))
        {
            Value v=tbl.lookup(var_name);
            if(v.getType().equals(new IntType()))
            {
                Value e=exp.eval(tbl,hp);
                if(e.getType().equals(new StringType()))
                {
                    if (filetbl.isDefined((StringValue) e))
                    {
                        BufferedReader br=filetbl.lookup((StringValue) e);
                        try {
                            String s = br.readLine();
                            if(s==null)
                            {
                                tbl.update(var_name,new IntValue(0));
                            }
                            else
                            {
                                tbl.update(var_name,new IntValue(Integer.parseInt(s)));
                            }
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
            }
            else
            {
                throw new MyException("The var: "+var_name+" need to be IntType");
            }
        }
        else
        {
            throw new MyException("The var: "+var_name+" is not defined");
        }
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new ReadFileStmt(exp.deepcopy(),var_name);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type Typeexp=exp.typecheck(typeEnv);
        if (Typeexp.equals(new StringType()))
        {
            return typeEnv;
        }
        else
            throw new MyException("ReadStmt: exp needs to be of type string!");
    }
}

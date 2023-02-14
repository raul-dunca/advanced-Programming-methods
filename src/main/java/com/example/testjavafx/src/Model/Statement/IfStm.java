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

public class IfStm implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStm(Exp e, IStmt t, IStmt el)
    {
        exp=e;
        thenS=t;
        elseS=el;
    }
    public String toString()
    {
        return "IF("+ exp+") THEN: " +thenS
                +" ELSE: "+elseS;
    }
    @Override
    public PrgState execute (PrgState state) throws MyException, DvisionByZeroExc {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        Value v=exp.eval(tbl,hp);

        if ( (v.getType()).equals(new BoolType()) )
        {
            BoolValue v2=(BoolValue)v;
            if(v2.getVal())
            {
                stk.push(thenS);
            }
            else
            {
                stk.push(elseS);
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
        return new IfStm(exp.deepcopy(),thenS.deepcopy(),elseS.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeexp=exp.typecheck(typeEnv);
        MyIDictionary<String,Type> newtypeEnv1=new MyDictionary<String,Type>();
        MyIDictionary<String,Type> newtypeEnv2=new MyDictionary<String,Type>();
        if (typeexp.equals(new BoolType()))
        {
            for(String k : typeEnv.getvkeys())
            {
                newtypeEnv1.add(k,typeEnv.lookup(k));
            }
            for(String k : typeEnv.getvkeys())
            {
                newtypeEnv2.add(k,typeEnv.lookup(k));
            }
            thenS.typecheck(newtypeEnv1);
            elseS.typecheck(newtypeEnv2);

            return typeEnv;

        }
        else
            throw new MyException("The IF condition is not of type boolean!");
    }
}

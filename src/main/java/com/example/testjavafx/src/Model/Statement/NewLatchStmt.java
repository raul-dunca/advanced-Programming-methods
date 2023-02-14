package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.MyAdts.MyILatchTable;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;

public class NewLatchStmt implements IStmt{
    String id;
    Exp exp;

    public NewLatchStmt(String i,Exp e)
    {
        id=i;
        exp=e;
    }
    public String toString()
    {
        return "newLatch("+id+", "+exp+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        MyILatchTable<Integer,Integer> latch=state.getLatchTable();

        Value v=exp.eval(tbl,hp);

        if((v.getType()).equals(new IntType()))
        {
            IntValue v1=(IntValue) v;
            int f=latch.getFreeloc();
            latch.add(f,v1.getVal());
            if(tbl.isDefined(id))
            {
                Type TypeId=(tbl.lookup(id)).getType();
                if (TypeId.equals(new IntType()))
                {
                    tbl.update(id,new IntValue(f));
                }
                else
                    throw new MyException(id+" is not an integer!");
            }
            else
                throw new MyException(id+" is not defined!");
        }
        else
            throw new MyException(exp+" is not an integer!");

        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new NewLatchStmt(id,exp);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar=typeEnv.lookup(id);
        Type typeexp=exp.typecheck(typeEnv);
        if (typevar.equals(new IntType()))
        {
            if(typeexp.equals(new IntType())) {
                return typeEnv;
            }
            else
                throw new MyException(exp+"is not an integer !");
        }
        else
            throw new MyException(id+"is not an integer !");
    }

}

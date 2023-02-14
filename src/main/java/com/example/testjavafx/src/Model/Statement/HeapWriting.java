package com.example.testjavafx.src.Model.Statement;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.RefType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.RefValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;

public class HeapWriting implements IStmt{
    Exp e;
    String id;

    public HeapWriting(Exp ex, String i)
    {
        e=ex;
        id=i;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIHeap<Integer, Value> hp=state.getHeap();
        MyIDictionary<String,Value> tbl= state.getSymTable();
        if (tbl.isDefined(id))
        {
            Type TypeId=(tbl.lookup(id)).getType();
            if (TypeId instanceof RefType)
            {
                RefValue v= (RefValue) tbl.lookup(id);
                if (hp.isOccupied(v.getAddres()))
                {
                    Value val=e.eval(tbl,hp);
                    if(val.getType().equals(((RefType) TypeId).getInner()))
                    {
                        hp.update(v.getAddres(),val);
                    }
                    else
                    {
                        throw new MyException("The expression need to be of type RefType");
                    }
                }
                else
                {
                    throw new MyException(v.getAddres()+" is an invalid address");
                }
            }
            else
            {
                throw new MyException("Variable "+id+" is not of type RefType");
            }
        }
        else
        {
            throw new MyException("Variable "+id+" is not defined!");
        }
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new HeapWriting(e.deepcopy(),id);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar=typeEnv.lookup(id);
        Type typeexp=e.typecheck(typeEnv);
        if (typevar.equals(new RefType(typeexp)))
        {
            return typeEnv;
        }
        throw new MyException("HEAPWR: right side does not have the same type as the left side");
    }

    public String toString() {
        return "HeapWriting: "+id+": "+e;
    }
}

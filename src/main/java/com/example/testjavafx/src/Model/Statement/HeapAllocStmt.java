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

public class HeapAllocStmt implements  IStmt{

    Exp exp;
    String id;
    public HeapAllocStmt(String i, Exp e)
    {
        exp=e;
        id=i;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {

        MyIHeap<Integer, Value> heap=state.getHeap();
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        if (symTbl.isDefined(id))
        {
            Type TypeId=(symTbl.lookup(id)).getType();

            if(TypeId instanceof RefType)
            {
                Value val=exp.eval(symTbl,heap);
                if(val.getType().equals(((RefType) TypeId).getInner()))
                {
                    int f=heap.getFreeloc();
                    heap.add(f,val);
                    symTbl.update(id,new RefValue(f, val.getType()));
                }
                else
                {
                    throw new MyException("Declared variable: "+id +" and type of the HeapAlloc expression do not match");
                }
            }
            else
            {
                throw new MyException("Declared variable: "+id +" needs to be of type Ref");
            }

        }

        else {
            throw new MyException("The used variable: "+id+" was not declared before!");
        }

        return null;
    }

    public String toString(){
        return "HeapAlloc Stmt: "+id+", "+exp;
    }

    @Override
    public IStmt deepcopy() {
        return new HeapAllocStmt(id,exp.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar=typeEnv.lookup(id);
        Type typeexp=exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typeexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right side does not have the same type as the left side");

    }

}

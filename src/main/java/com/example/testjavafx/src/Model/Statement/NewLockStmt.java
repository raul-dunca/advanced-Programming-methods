package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.MyAdts.MyILockTable;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.RefType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.RefValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStmt implements IStmt{
    String id;
    Lock myLock=new ReentrantLock();
    public NewLockStmt(String i)
    {
        id=i;
    }
    public String toString(){
        return "newLock("+id+")";
    }
    @Override
    public  PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        //myLock.lock();
        MyILockTable<Integer, Integer> lckTbl=state.getLockTable();
        MyIDictionary<String,Value> symTbl= state.getSymTable();

        if (symTbl.isDefined(id)) {
            Type TypeId=(symTbl.lookup(id)).getType();
            if(TypeId.equals(new IntType())) {
                int f = lckTbl.getFreeloc();
                lckTbl.add(f, -1);
                symTbl.update(id, new IntValue(f));
            }
            else
                throw new MyException("Variable: " + id + " needs to be of type int!");
        }
        else
            throw new MyException("Variable: " + id + " not defined!");
        //myLock.unlock();
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new NewLockStmt(id);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        if (typevar.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Variable: " + id + " needs to be of type int!");
    }

}



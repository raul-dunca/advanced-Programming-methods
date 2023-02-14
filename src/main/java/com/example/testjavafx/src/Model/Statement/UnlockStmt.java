package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyILockTable;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStmt implements IStmt{
    String id;
    Lock myLock=new ReentrantLock();
    public UnlockStmt(String i)
    {
        id=i;
    }
    public String toString(){
        return "Unlock("+id+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        //myLock.lock();
        MyILockTable<Integer, Integer> lckTbl=state.getLockTable();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        IntValue foundIndex= (IntValue) symTbl.lookup(id);
        if (symTbl.isDefined(id)) {
            Type TypeId = (symTbl.lookup(id)).getType();
            if (TypeId.equals(new IntType())) {
                if (lckTbl.isOccupied(foundIndex.getVal())) {
                    int value = lckTbl.lookup(foundIndex.getVal());
                    if (value != -1)
                        lckTbl.update(foundIndex.getVal(), -1);

                } else
                    throw new MyException(foundIndex + "not in LockTable !");
            } else
                throw new MyException("Variable: " + id + " needs to be of type int!");
        }
        else throw new MyException("Variable: " + id + " not defined!");
        //myLock.unlock();
        return null;

    }

    @Override
    public IStmt deepcopy() {
        return new UnlockStmt(id);
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


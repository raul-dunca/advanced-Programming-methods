package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyILockTable;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.RefType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStmt implements IStmt{
    String id;
    Lock mylock=new ReentrantLock();
    public LockStmt(String i)
    {
        id=i;
    }
    public String toString(){
        return "Lock("+id+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        //mylock.lock();
        MyILockTable<Integer, Integer> lckTbl=state.getLockTable();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIStack<IStmt> stk=state.getStk();
        IntValue foundIndex= (IntValue) symTbl.lookup(id);
        if (symTbl.isDefined(id)) {
            Type TypeId=(symTbl.lookup(id)).getType();
            if(TypeId.equals(new IntType())) {

                if (lckTbl.isOccupied(foundIndex.getVal())) {
                    int value = lckTbl.lookup(foundIndex.getVal());
                    if (value == -1) {
                        lckTbl.update(foundIndex.getVal(), state.get_id());
                    } else {
                        stk.push(this);
                    }
                }
                else
                    throw new MyException(foundIndex + "not in LockTable !");
            }
            else throw new MyException("Variable: " + id + " needs to be of type int!");
        }
        else
        {
            throw new MyException("Variable: " + id + " not defined!");

        }
        //mylock.unlock();
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new LockStmt(id);
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

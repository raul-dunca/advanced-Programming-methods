package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyILatchTable;
import com.example.testjavafx.src.Model.MyAdts.MyIList;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;

public class CountDownStmt implements IStmt{
    String id;
    public CountDownStmt(String i)
    {
        id=i;
    }
    public String toString()
    {
        return "countDown("+id+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyILatchTable<Integer,Integer> latch=state.getLatchTable();
        MyIList<Value> out=state.getList();
        if (tbl.isDefined(id)) {
            Type TypeId=(tbl.lookup(id)).getType();
            if (TypeId.equals(new IntType()))
            {
                IntValue a= (IntValue) tbl.lookup(id);
                if(latch.isOccupied(a.getVal()))
                {
                    if (latch.lookup(a.getVal())>0)
                    {
                        latch.update(a.getVal(),latch.lookup(a.getVal())-1);
                    }
                    out.append(new IntValue(state.get_id()));
                }
                else
                    throw new MyException(a+"not defined in the LatchTable !");
            }
            else
                throw new MyException(id+"is not od type int");
        }
        else
            throw new MyException(id+"is not defined !");
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new CountDownStmt(id);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar=typeEnv.lookup(id);
        if (typevar.equals(new IntType()))
        {
            return typeEnv;
        }
        else
            throw new MyException(id+"is not an integer !");

    }
}

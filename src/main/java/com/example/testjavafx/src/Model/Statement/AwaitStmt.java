package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyILatchTable;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.IOException;

public class AwaitStmt implements IStmt {
    String id;
    public AwaitStmt(String i)
    {
        id=i;
    }
    public String toString()
    {
        return "await("+id+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String, Value> tbl=state.getSymTable();
        MyILatchTable<Integer,Integer> latch=state.getLatchTable();

        if (tbl.isDefined(id)) {
            Type TypeId=(tbl.lookup(id)).getType();
            if (TypeId.equals(new IntType()))
            {
               IntValue a= (IntValue) tbl.lookup(id);
               if(latch.isOccupied(a.getVal()))
               {
                    if (latch.lookup(a.getVal())!=0)
                    {
                        stk.push(this);
                    }
               }
               else
                   throw new MyException(a+"not defined in the LatchTable !");
            }
            else
                throw new MyException(id+"is not of type int");
        }
        else
            throw new MyException(id+"is not defined !");
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new AwaitStmt(id);
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

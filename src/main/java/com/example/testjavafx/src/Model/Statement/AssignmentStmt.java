package com.example.testjavafx.src.Model.Statement;

import com.example.testjavafx.src.Exception.DvisionByZeroExc;
import com.example.testjavafx.src.Exception.MyException;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.MyDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.BoolType;
import com.example.testjavafx.src.Model.Type.Type;

import java.io.IOException;

public class AssignmentStmt implements IStmt{
    String id;
    Exp exp1;
    Exp exp2;
    Exp exp3;
    public AssignmentStmt(String i, Exp e1, Exp e2, Exp e3)
    {
        id=i;
        exp1=e1;
        exp2=e2;
        exp3=e3;
    }
    public String toString()
    {
        return id+"="+exp1+" ? "+exp2+": "+exp3;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc, IOException {
        MyIStack<IStmt> stk=state.getStk();
        stk.push(new IfStm(exp1,new AssignStmt(id,exp2),new AssignStmt(id,exp3)));

        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new AssignmentStmt(id,exp1.deepcopy(),exp2.deepcopy(),exp3.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeexp1=exp1.typecheck(typeEnv);
        Type typeexp2=exp2.typecheck(typeEnv);
        Type typeexp3=exp3.typecheck(typeEnv);

        if (typeexp1.equals(new BoolType()))
        {
            if (typeEnv.isDefined(id)) {
                Type var1 = typeEnv.lookup(id);
                if(var1.equals(typeexp2) && var1.equals(typeexp3)) {
                    return typeEnv;
                }
                else
                    throw new MyException(id+", "+exp2+"and "+exp3+"don't have the same type");
            }
            else
                throw new MyException(id+"is not defined!");
        }
        else
            throw new MyException(exp1+"is not of type bool!");
    }
}


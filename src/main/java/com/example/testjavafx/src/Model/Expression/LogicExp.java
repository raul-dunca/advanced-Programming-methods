package com.example.testjavafx.src.Model.Expression;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.Type.BoolType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.BoolValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.util.Objects;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    int op;

    String keep;
    public LogicExp(Exp ex1, Exp ex2, String o)
    {
        e1=ex1;
        e2=ex2;
        keep=o;
        if(Objects.equals(o, "&&"))
            op=1;
        if(Objects.equals(o, "||"))
            op=2;
    }

    public String toString() {
        return "Logic exp: " + e1 + " "+keep+" " + e2;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer,Value> hp) throws MyException, DvisionByZeroExc {
        Value v1, v2;
        v1 = e1.eval(tbl,hp);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl,hp);
            if (v1.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1)
                    return new BoolValue(n1 && n2);
                if (op == 2)
                    return new BoolValue(n1 || n2);
            } else {
                throw new MyException("second operand is not a boolean");
            }
        } else {
            throw new MyException("first operand is not a boolean");
        }
        return null;
    }

    @Override
    public Exp deepcopy() {
        return new LogicExp(e1.deepcopy(),e2.deepcopy(),keep);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1=e1.typecheck(typeEnv);
        t2=e2.typecheck(typeEnv);

        if (t1.equals(new BoolType()))
        {
            if (t2.equals(new BoolType()))
            {
                return new BoolType();
            }
            else {
                throw new MyException("second operand is not a boolean!");

            }
        }
        else
            throw new MyException("first operand is not a boolean!");
    }
}

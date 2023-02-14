package com.example.testjavafx.src.Model.Expression;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op;

    char keep;

    public ArithExp(char o,Exp ex1, Exp ex2)
    {
        e1=ex1;
        e2=ex2;
        keep=o;
        if(o=='+')
        {
            op=1;
        }
        if(o=='-')
        {
            op=2;
        }
        if(o=='*')
        {
            op=3;
        }
        if(o=='/')
        {
            op=4;
        }

    }
    public String toString() {
        return "Arithmetic exp: " + e1 + " "+keep+" " + e2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer,Value> hp) throws MyException, DvisionByZeroExc {
        Value v1, v2;
        v1 = e1.eval(tbl,hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,hp);
            if (v1.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1)
                    return new IntValue(n1 + n2);
                if (op == 2)
                    return new IntValue(n1 - n2);
                if (op == 3)
                    return new IntValue(n1 * n2);
                if (op == 4) {
                    if (n2 == 0)
                        throw new DvisionByZeroExc("Division by zero");
                    else
                        return new IntValue(n1 / n2);
                }


            } else {
                throw new MyException("second operand is not an integer");
            }
        } else {
            throw new MyException("first operand is not an integer");
        }
        return null;
    }

    @Override
    public Exp deepcopy() {
        return new ArithExp(keep,e1.deepcopy(),e2.deepcopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1=e1.typecheck(typeEnv);
        t2=e2.typecheck(typeEnv);
        if (t1.equals(new IntType()))
        {
            if (t2.equals(new IntType()))
                return new IntType();
            else
            {
                throw new MyException("second operand is not an integer!");
            }
        }
        else
        {
            throw new MyException("first operand is not an integer !");
        }
    }
}


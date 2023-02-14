package com.example.testjavafx.src.Model.Expression;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.Type.BoolType;
import com.example.testjavafx.src.Model.Type.IntType;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.BoolValue;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.util.Objects;

public class RelationalExp implements Exp{
    Exp e1;
    Exp e2;
    String op;
    public RelationalExp(String o, Exp ex1, Exp ex2)
    {
        op=o;
        e1=ex1;
        e2=ex2;
    }
    public String toString()
    {
        return "Relational Exp: "+e1+op+e2;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer,Value> hp) throws MyException, DvisionByZeroExc {
        Value v1,v2;
        v1=e1.eval(tbl,hp);
        boolean ok=true;
        if(v1.getType().equals(new IntType()))
        {
            v2=e2.eval(tbl,hp);
            if(v2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(Objects.equals(op, "<"))
                {
                    if (n1<n2)
                    {
                        return new BoolValue(true);
                    }
                    else {
                        return new BoolValue(false);
                    }
                }
                if(Objects.equals(op, "<="))
                {
                    ok=false;
                    if (n1<=n2)
                    {
                        return new BoolValue(true);
                    }
                    else {
                        return new BoolValue(false);
                    }
                }
                if(Objects.equals(op, "=="))
                {
                    ok=false;
                    if (n1==n2)
                    {
                        return new BoolValue(true);
                    }
                    else {
                        return new BoolValue(false);
                    }
                }
                if(Objects.equals(op, "!="))
                {
                    ok=false;
                    if (n1!=n2)
                    {
                        return new BoolValue(true);
                    }
                    else {
                        return new BoolValue(false);
                    }
                }
                if(Objects.equals(op, ">="))
                {
                    ok=false;
                    if (n1>=n2)
                    {
                        return new BoolValue(true);
                    }
                    else {
                        return new BoolValue(false);
                    }
                }
                if(Objects.equals(op, ">"))
                {
                    ok=false;
                    if (n1>n2)
                    {
                        return new BoolValue(true);
                    }
                    else {
                        return new BoolValue(false);
                    }
                }
                if(ok)
                {
                    throw new MyException("Invalid operation!");
                }
            }
            else
            {
                throw new MyException("second operand is not an integer");
            }
        }
        else
        {
            throw new MyException("first operand is not an integer");
        }
        return null;
    }

    @Override
    public Exp deepcopy() {
        return new RelationalExp(op,e1.deepcopy(),e2.deepcopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1=e1.typecheck(typeEnv);
        t2=e2.typecheck(typeEnv);
        if (t1.equals(new IntType()))
        {
            if (t2.equals(new IntType()))
                return new BoolType();
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

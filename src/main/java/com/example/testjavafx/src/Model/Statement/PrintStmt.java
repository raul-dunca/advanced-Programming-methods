package com.example.testjavafx.src.Model.Statement;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.MyAdts.MyIList;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;

public class PrintStmt implements IStmt {
    Exp exp;
    public PrintStmt(Exp e)
    {
        exp=e;
    }
    public String toString(){
        return "print ("+ exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String,Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        //stk.push(stm);
        //stk.pop();
        MyIList<Value> l=state.getList();
        l.append(exp.eval(tbl,hp));
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new PrintStmt(exp.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}

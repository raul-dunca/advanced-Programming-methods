package com.example.testjavafx.src.Model.Statement;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.Expression.Exp;
import com.example.testjavafx.src.Model.MyAdts.MyIDictionary;
import com.example.testjavafx.src.Model.MyAdts.MyIHeap;
import com.example.testjavafx.src.Model.MyAdts.MyIStack;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Type.Type;
import com.example.testjavafx.src.Model.Value.Value;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;
    public AssignStmt(String i, Exp e)
    {
        id=i;
        exp=e;
    }
    public String toString(){
        return "Assign Stmt: "+id+"="+exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, DvisionByZeroExc {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        MyIHeap<Integer,Value> hp=state.getHeap();
        if (symTbl.isDefined(id))
        {
            Value val=exp.eval(symTbl,hp);
            Type TypeId=(symTbl.lookup(id)).getType();
            if((val.getType()).equals(TypeId))
            {
                symTbl.update(id,val);
            }
            else
                throw new MyException ("declared type of variable"+id+" and type of the assigned expression do not match");

        }
        else
        {
            throw new MyException("The used variable: "+id+" was not declared before!");
        }

        return null;
    }

    @Override
    public IStmt deepcopy() {
       return  new AssignStmt(id,exp.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar=typeEnv.lookup(id);
        Type typeexp=exp.typecheck(typeEnv);
        if (typevar.equals(typeexp))
        {
            return typeEnv;
        }
        else
            throw new MyException("Assignment: right side does not match left side's type!");
    }

}

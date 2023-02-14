package com.example.testjavafx.src.Model;

import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.MyAdts.*;
import com.example.testjavafx.src.Model.Statement.IStmt;
import com.example.testjavafx.src.Model.Value.StringValue;
import com.example.testjavafx.src.Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIStack<MyIDictionary<String, Value>> stackOfsymTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;

    MyIHeap<Integer,Value> heap;

    MyILatchTable<Integer,Integer> latchTable;

    MyILockTable<Integer,Integer> lockTable;

    MyIProcTable<String, ArrayList<String>,IStmt> procTable;
    IStmt originalProgram;

    int id;
    static int helper=0;
    public boolean isNotCompleted()
    {
        if (exeStack.isempty())
        return false;
        else
            return true;


    }
    public PrgState oneStep() throws MyException, DvisionByZeroExc, StackEmptyExc, IOException {
        if(exeStack.isempty()) throw new StackEmptyExc("prgstate stack is empty");
        IStmt crtStmt= exeStack.pop();
        //System.out.println(crtStmt);
        return crtStmt.execute(this);
    }
    public int get_id()
    {
        return id;
    }
    public String toString()
    {
        String r=new String();
        r+="ID of the program: "+id;
        r+="\n";
        r+="ExeStack: \n";
        /*MyStack<IStmt> copystack= new MyStack<IStmt>();
        while (!exeStack.isempty())
        {
            IStmt st=exeStack.pop();
            r+=st.toString()+"\n";
            copystack.push(st);
        }
        while (!copystack.isempty())
        {
            IStmt st=copystack.pop();
            exeStack.push(st);
        }*/
        r+=exeStack.tostr();
        r+="Symbols Table: \n";
        r+=stackOfsymTable.top().tostr();
        r+="\nOutput: \n";
        /*for(int i=0;i<out.size();i++)
        {
            r+=out.get_index(i).toString();
        }*/
        r+=out.tostr();
        r+="File Table: \n";
        r+=fileTable.tostr();
        r+="\n";
        r+="Heap: \n";
        r+=heap.tostr();
        r+="\n";
        r+="LatchTable: \n";
        r+=latchTable.tostr();
        r+="\n";
        r+="LockTable: \n";
        r+=lockTable.tostr();
        r+="\n";
        r+="\n";
        return r;



    }

    static synchronized void setHelper() {
        helper++;
    }

    public PrgState(MyIStack<IStmt> stk, MyIStack<MyIDictionary<String, Value>> symtbl, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> filetbl, MyIHeap<Integer, Value> h,MyILatchTable<Integer,Integer>  l,MyILockTable<Integer,Integer> lck,MyIProcTable<String,ArrayList<String>,IStmt> p, IStmt prg){
        exeStack=stk;
        stackOfsymTable=symtbl;
        out=ot;
        fileTable=filetbl;
        heap=h;
        originalProgram=prg.deepcopy();
        latchTable=l;
        lockTable=lck;
        procTable=p;
        setHelper();
        id=helper;
     //   originalProgram=prg;  //deepcopy?
        stk.push(prg);
    }
    public MyIStack<IStmt> getStk()
    {
        return exeStack;
    }
    public MyIList<Value> getList()
    {
        return out;
    }

    public MyIDictionary<String,Value> getSymTable()
    {
        return stackOfsymTable.top();
    }

    public MyIStack<MyIDictionary<String,Value>> getAllSymTables()
    {
        return stackOfsymTable;
    }
    public MyIDictionary<StringValue,BufferedReader> getFileTable(){return fileTable;}

    public MyIHeap<Integer,Value> getHeap(){return heap;}
    public MyILatchTable<Integer,Integer> getLatchTable(){return latchTable;}

    public MyILockTable<Integer,Integer> getLockTable(){return lockTable;}

    public MyIProcTable<String,ArrayList<String>,IStmt> getProcTable(){return procTable;}
}

package com.example.testjavafx.src.View;

import com.example.testjavafx.src.Controller.Controller;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.Expression.*;
import com.example.testjavafx.src.Model.MyAdts.*;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Statement.*;
import com.example.testjavafx.src.Model.Type.*;
import com.example.testjavafx.src.Model.Value.BoolValue;
import com.example.testjavafx.src.Model.Value.IntValue;
import com.example.testjavafx.src.Model.Value.StringValue;
import com.example.testjavafx.src.Model.Value.Value;
import com.example.testjavafx.src.Repository.Repo;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) {
        TextMenu menu = new TextMenu();


/*        try {
            MyStack<IStmt> stk1 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl1 = new MyDictionary<String, Value>();
            MyList<Value> out1 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl1 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap1 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict1 = new MyDictionary<String, Type>();
            IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValuExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
            ex1.typecheck(dict1);
            PrgState prg1 = new PrgState(stk1, tbl1, out1, filetbl1, heap1, ex1);
            Repo r1 = new Repo(prg1, "C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c1 = new Controller(r1);
            menu.addCommand(new RunCommand("1", ex1.toString(), c1));
        } catch (MyException me1) {
            System.err.println("InterruptedException" + me1);
        }

        try {
            MyStack<IStmt> stk2 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl2 = new MyDictionary<String, Value>();
            MyList<Value> out2 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl2 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap2 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict2 = new MyDictionary<String, Type>();
            IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp('+', new ValuExp(new IntValue(2)), new
                                    ArithExp('*', new ValuExp(new IntValue(3)), new ValuExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValuExp(new
                                            IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            ex2.typecheck(dict2);
            PrgState prg2 = new PrgState(stk2, tbl2, out2, filetbl2, heap2, ex2);
            Repo r2 = new Repo(prg2, "C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c2 = new Controller(r2);
            menu.addCommand(new RunCommand("2", ex2.toString(), c2));
        } catch (MyException me2) {
            System.err.println("InterruptedException" + me2);
        }

        try {
            MyStack<IStmt> stk3 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl3 = new MyDictionary<String, Value>();
            MyList<Value> out3 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl3 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap3 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict3 = new MyDictionary<String, Type>();
            IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValuExp(new BoolValue(true))),
                                    new CompStmt(new IfStm(new VarExp("a"), new AssignStmt("v", new ValuExp(new
                                            IntValue(2))), new AssignStmt("v", new ValuExp(new IntValue(3)))), new PrintStmt(new
                                            VarExp("v"))))));
            ex3.typecheck(dict3);
            PrgState prg3 = new PrgState(stk3, tbl3, out3, filetbl3, heap3, ex3);
            Repo r3 = new Repo(prg3, "C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c3 = new Controller(r3);
            menu.addCommand(new RunCommand("3", ex3.toString(), c3));
        } catch (MyException me3) {
            System.err.println("InterruptedException" + me3);
        }
        try {
            MyStack<IStmt> stk4 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl4 = new MyDictionary<String, Value>();
            MyList<Value> out4 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl4 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap4 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict4 = new MyDictionary<String, Type>();
            IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValuExp(new StringValue("C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\src\\test.in"))), new CompStmt(new OpenFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));
            ex4.typecheck(dict4);
            PrgState prg4 = new PrgState(stk4, tbl4, out4, filetbl4, heap4, ex4);
            Repo r4 = new Repo(prg4, "C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c4 = new Controller(r4);
            menu.addCommand(new RunCommand("4", ex4.toString(), c4));
        } catch (MyException me4) {
            System.err.println("InterruptedException" + me4);
        }

        try {
            MyStack<IStmt> stk5 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl5 = new MyDictionary<String, Value>();
            MyList<Value> out5 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl5 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap5 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict5 = new MyDictionary<String, Type>();
            IStmt ex5 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValuExp(new IntValue(100))), new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp('+', new ValuExp(new IntValue(24)), new ValuExp(new IntValue(44)))), new PrintStmt(new RelationalExp(">=", new VarExp("a"), new VarExp("v")))))));
            ex5.typecheck(dict5);
            PrgState prg5 = new PrgState(stk5, tbl5, out5, filetbl5, heap5, ex5);
            Repo r5 = new Repo(prg5, "C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c5 = new Controller(r5);
            menu.addCommand(new RunCommand("5", ex5.toString(), c5));
        } catch (MyException me5) {
            System.err.println("InterruptedException" + me5);
        }

        try {
            MyStack<IStmt> stk6 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl6 = new MyDictionary<String, Value>();
            MyList<Value> out6 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl6 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap6 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict6 = new MyDictionary<String, Type>();
            IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValuExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt("a", new VarExp("v")), new PrintStmt(new VarExp("a"))))));
            ex6.typecheck(dict6);
            PrgState prg6 = new PrgState(stk6, tbl6, out6, filetbl6, heap6, ex6);
            Repo r6 = new Repo(prg6, "C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c6 = new Controller(r6);
            menu.addCommand(new RunCommand("6", ex6.toString(), c6));
        } catch (MyException me6) {
            System.err.println("InterruptedException" + me6);
        }

        try {
            MyStack<IStmt> stk7 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl7 = new MyDictionary<String, Value>();
            MyList<Value> out7 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl7 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap7 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict7 = new MyDictionary<String, Type>();
            IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValuExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt("a", new VarExp("v")), new PrintStmt(new ArithExp('+', new HeapReading(new HeapReading(new VarExp("a"))), new ValuExp(new IntValue(5))))))));
            ex7.typecheck(dict7);
            PrgState prg7 = new PrgState(stk7, tbl7, out7, filetbl7, heap7, ex7);
            Repo r7 = new Repo(prg7, "C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c7 = new Controller(r7);
            menu.addCommand(new RunCommand("7", ex7.toString(), c7));
        } catch (MyException me7) {
            System.err.println("InterruptedException" + me7);
        }

        try {
            MyStack<IStmt> stk8 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl8 = new MyDictionary<String, Value>();
            MyList<Value> out8 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl8 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap8 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict8 = new MyDictionary<String, Type>();
            IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValuExp(new IntValue(20))), new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))), new CompStmt(new HeapWriting(new ValuExp(new IntValue(30)), "v"), new PrintStmt(new ArithExp('+', new HeapReading(new VarExp("v")), new ValuExp(new IntValue(5))))))));
            PrgState prg8 = new PrgState(stk8, tbl8, out8, filetbl8, heap8, ex8);
            ex8.typecheck(dict8);
            Repo r8 = new Repo(prg8, "C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c8 = new Controller(r8);
            menu.addCommand(new RunCommand("8", ex8.toString(), c8));
        } catch (MyException me8) {
            System.err.println("InterruptedException" + me8);
        }
        try
        {
        MyStack<IStmt> stk9 = new MyStack<IStmt>();
        MyDictionary<String, Value> tbl9 = new MyDictionary<String, Value>();
        MyList<Value> out9 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> filetbl9 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap<Integer,Value> heap9=new MyHeap<Integer,Value>();
        MyIDictionary<String, Type> dict9 = new MyDictionary<String, Type>();
        IStmt ex9=new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValuExp(new IntValue(4))),new CompStmt(new WhileStmt(new RelationalExp(">",new VarExp("v"),new ValuExp(new IntValue(0))),new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValuExp(new IntValue(1)))))),new PrintStmt(new VarExp("v")))));
        ex9.typecheck(dict9);
        PrgState prg9=new PrgState(stk9,tbl9,out9,filetbl9,heap9,ex9);
        Repo r9=new Repo(prg9,"C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
        Controller c9=new Controller(r9);
        menu.addCommand(new RunCommand("9", ex9.toString(), c9));
        }
        catch (MyException me9)
        {
            System.err.println("InterruptedException" + me9);
        }

        try
        {
        MyStack<IStmt> stk10 = new MyStack<IStmt>();
        MyDictionary<String, Value> tbl10 = new MyDictionary<String, Value>();
        MyList<Value> out10 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> filetbl10 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap<Integer,Value> heap10=new MyHeap<Integer,Value>();
        MyIDictionary<String, Type> dict10 = new MyDictionary<String, Type>();
        IStmt ex10=new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValuExp(new IntValue(20))),new CompStmt(new PrintStmt(new HeapReading(new VarExp("v"))),new CompStmt(new HeapAllocStmt("v",new ValuExp(new IntValue(100))),new PrintStmt(new HeapReading(new VarExp("v")))))));
        ex10.typecheck(dict10);
        PrgState prg10=new PrgState(stk10,tbl10,out10,filetbl10,heap10,ex10);
        Repo r10=new Repo(prg10,"C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
        Controller c10=new Controller(r10);
        menu.addCommand(new RunCommand("10", ex10.toString(), c10));
        }
        catch (MyException me10) {
            System.err.println("InterruptedException" + me10);
        }

        try{
        MyStack<IStmt> stk11 = new MyStack<IStmt>();
        MyDictionary<String, Value> tbl11 = new MyDictionary<String, Value>();
        MyList<Value> out11 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> filetbl11 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap<Integer,Value> heap11=new MyHeap<Integer,Value>();
        MyIDictionary<String, Type> dict11 = new MyDictionary<String, Type>();
        IStmt ex11=new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValuExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocStmt("a",new VarExp("v")),new CompStmt(new HeapAllocStmt("v",new ValuExp(new IntValue(150))),new PrintStmt(new HeapReading(new HeapReading(new VarExp("a")))))))));
        ex11.typecheck(dict11);
        PrgState prg11=new PrgState(stk11,tbl11,out11,filetbl11,heap11,ex11);
        Repo r11=new Repo(prg11,"C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
        Controller c11=new Controller(r11);
        menu.addCommand(new RunCommand("11", ex11.toString(), c11));
        }
        catch (MyException me11) {
            System.err.println("InterruptedException" + me11);
        }

        try{
        MyStack<IStmt> stk12 = new MyStack<IStmt>();
        MyDictionary<String, Value> tbl12 = new MyDictionary<String, Value>();
        MyList<Value> out12 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> filetbl12 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap<Integer,Value> heap12=new MyHeap<Integer,Value>();
        MyIDictionary<String, Type> dict12 = new MyDictionary<String, Type>();
        IStmt ex12=new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new AssignStmt("v",new ValuExp(new IntValue(10))),new CompStmt(new HeapAllocStmt("a",new ValuExp(new IntValue(22))),new CompStmt(new ForkStmt(new CompStmt(new HeapWriting(new ValuExp(new IntValue(30)),"a"),new CompStmt(new AssignStmt("v",new ValuExp(new IntValue(32))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReading(new VarExp("a"))))))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReading(new VarExp("a")))))))));
        ex12.typecheck(dict12);
        PrgState prg12=new PrgState(stk12,tbl12,out12,filetbl12,heap12,ex12);
        Repo r12=new Repo(prg12,"C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
        Controller c12=new Controller(r12);
        menu.addCommand(new RunCommand("12", ex12.toString(), c12));
        }
        catch (MyException me12) {
            System.err.println("InterruptedException" + me12);
        }

        try{
        MyStack<IStmt> stk13 = new MyStack<IStmt>();
        MyDictionary<String, Value> tbl13 = new MyDictionary<String, Value>();
        MyList<Value> out13 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> filetbl13 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap<Integer,Value> heap13=new MyHeap<Integer,Value>();
        MyIDictionary<String, Type> dict13 = new MyDictionary<String, Type>();
        IStmt ex13=new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("a",new ValuExp(new IntValue(20))),new CompStmt(new ForkStmt(new CompStmt(new HeapAllocStmt("a",new ValuExp(new IntValue(100))),new PrintStmt(new HeapReading(new VarExp("a"))))),new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValuExp(new IntValue(10))),new PrintStmt(new VarExp("v")))))));
        ex13.typecheck(dict13);
        PrgState prg13=new PrgState(stk13,tbl13,out13,filetbl13,heap13,ex13);
        Repo r13=new Repo(prg13,"C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
        Controller c13=new Controller(r13);
        menu.addCommand(new RunCommand("13", ex13.toString(), c13));
        }
        catch (MyException me13) {
            System.err.println("InterruptedException" + me13);
        }

        try{
            MyStack<IStmt> stk14 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl14 = new MyDictionary<String, Value>();
            MyList<Value> out14 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl14 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer,Value> heap14=new MyHeap<Integer,Value>();
            MyIDictionary<String, Type> dict14 = new MyDictionary<String, Type>();
            IStmt ex14 = new CompStmt(new VarDeclStmt("v", new BoolType()), new CompStmt(new AssignStmt("v", new ValuExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
            ex14.typecheck(dict14);
            PrgState prg14=new PrgState(stk14,tbl14,out14,filetbl14,heap14,ex14);
            Repo r14=new Repo(prg14,"C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c14=new Controller(r14);
            menu.addCommand(new RunCommand("14", ex14.toString(), c14));
        }
        catch (MyException me14) {
            System.err.println("InterruptedException" + me14);
        }

        try{
            MyStack<IStmt> stk15 = new MyStack<IStmt>();
            MyDictionary<String, Value> tbl15 = new MyDictionary<String, Value>();
            MyList<Value> out15 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl15 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer,Value> heap15=new MyHeap<Integer,Value>();
            MyIDictionary<String, Type> dict15 = new MyDictionary<String, Type>();
            IStmt ex15 = new CompStmt(new VarDeclStmt("a",new BoolType()),new CompStmt(new AssignStmt("a",new ValuExp(new BoolValue(true))),new CompStmt(new WhileStmt(new VarExp("a"),new VarDeclStmt("v",new IntType())),new PrintStmt(new VarExp("v")))));
            ex15.typecheck(dict15);
            PrgState prg15=new PrgState(stk15,tbl15,out15,filetbl15,heap15,ex15);
            Repo r15=new Repo(prg15,"C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\logfile.txt");
            Controller c15=new Controller(r15);
            menu.addCommand(new RunCommand("15", ex15.toString(), c15));
        }
        catch (MyException me15) {
            System.err.println("InterruptedException" + me15);
        }

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.show();*/


    }
}

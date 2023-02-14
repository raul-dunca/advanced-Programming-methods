package com.example.testjavafx;

import com.example.testjavafx.src.Controller.Controller;
import com.example.testjavafx.src.Exception.MyException;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import kotlin.Pair;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController  implements Initializable{
    Controller c1;
    Controller c2;
    Controller c3;
    Controller c4;
    Controller c5;
    Controller c6;
    Controller c7;
    Controller c8;
    Controller c9;
    @FXML
    private ListView<String> ListViewofIStmt;
    MainController mainCtrl;
    public void setMainWindowController(MainController mainWindowController){
        mainCtrl=mainWindowController;
    }
    @FXML
    protected void runPrg() {
        int nr=ListViewofIStmt.getSelectionModel().getSelectedIndex();
        nr++;
        switch(nr)
        {
            case 1:
                mainCtrl.setController(c1);
                break;
            case 2:
                mainCtrl.setController(c2);
                break;
            case 3:
                mainCtrl.setController(c3);
                break;
            case 4:
                mainCtrl.setController(c4);
                break;
            case 5:
                mainCtrl.setController(c5);
                break;
            case 6:
                mainCtrl.setController(c6);
                break;
            case 7:
                mainCtrl.setController(c7);
                break;
            case 8:
                mainCtrl.setController(c8);
                break;
            case 9:
                mainCtrl.setController(c9);
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select witch program you want to run!", ButtonType.OK);
                alert.showAndWait();
        }
        //ListViewofIStmt.setMouseTransparent( true );

    }
    public int getnrofrow()
    {
        return ListViewofIStmt.getSelectionModel().getSelectedIndex();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MyStack<IStmt> stk1 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl1 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym1=new MyDictionary<String, Value>() ;
            tbl1.push(sym1);
            MyList<Value> out1 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl1 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap1 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict1 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch1=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck1=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl1=new  MyProcTable<String, ArrayList<String>,IStmt>();
            IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValuExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
            ex1.typecheck(dict1);
            PrgState prg1 = new PrgState(stk1, tbl1, out1, filetbl1, heap1, latch1,lck1,prctbl1,ex1);
            Repo r1 = new Repo(prg1, "C:\\Users\\testJavaFx\\logfile.txt");
            c1 = new Controller(r1);
            ListViewofIStmt.getItems().add(ex1.toString());
        } catch (MyException me1) {
            System.err.println("InterruptedException" + me1);
        }
        try {
            MyStack<IStmt> stk2 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl2 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym2=new MyDictionary<String, Value>() ;
            tbl2.push(sym2);
            MyList<Value> out2 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl2 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap2 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict2 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch2=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck2=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl2=new  MyProcTable<String, ArrayList<String>,IStmt>();
            IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp('+', new ValuExp(new IntValue(2)), new
                                    ArithExp('*', new ValuExp(new IntValue(3)), new ValuExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValuExp(new
                                            IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            ex2.typecheck(dict2);
            PrgState prg2 = new PrgState(stk2, tbl2, out2, filetbl2, heap2,latch2,lck2,prctbl2, ex2);
            Repo r2 = new Repo(prg2, "C:\\Users\\testJavaFx\\logfile.txt");
            c2 = new Controller(r2);
            ListViewofIStmt.getItems().add(ex2.toString());
        } catch (MyException me2) {
            System.err.println("InterruptedException" + me2);
        }

        try {
            MyStack<IStmt> stk3 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl3 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym3=new MyDictionary<String, Value>() ;
            tbl3.push(sym3);
            MyList<Value> out3 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl3 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap3 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict3 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch3=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck3=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl3=new  MyProcTable<String, ArrayList<String>,IStmt>();
            IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValuExp(new BoolValue(true))),
                                    new CompStmt(new IfStm(new VarExp("a"), new AssignStmt("v", new ValuExp(new
                                            IntValue(2))), new AssignStmt("v", new ValuExp(new IntValue(3)))), new PrintStmt(new
                                            VarExp("v"))))));
            ex3.typecheck(dict3);
            PrgState prg3 = new PrgState(stk3, tbl3, out3, filetbl3, heap3, latch3,lck3,prctbl3,ex3);
            Repo r3 = new Repo(prg3, "C:\\Users\\testJavaFx\\logfile.txt");
            c3 = new Controller(r3);
            ListViewofIStmt.getItems().add(ex3.toString());
        } catch (MyException me3) {
            System.err.println("InterruptedException" + me3);
        }
        try {
            MyStack<IStmt> stk4 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl4 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym4=new MyDictionary<String, Value>() ;
            tbl4.push(sym4);
            MyList<Value> out4 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl4 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer, Value> heap4 = new MyHeap<Integer, Value>();
            MyIDictionary<String, Type> dict4 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch4=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck4=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl4=new  MyProcTable<String, ArrayList<String>,IStmt>();
            IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValuExp(new StringValue("C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\src\\test.in"))), new CompStmt(new OpenFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));
            ex4.typecheck(dict4);
            PrgState prg4 = new PrgState(stk4, tbl4, out4, filetbl4, heap4,latch4,lck4,prctbl4, ex4);
            Repo r4 = new Repo(prg4, "C:\\Users\\testJavaFx\\logfile.txt");
            c4 = new Controller(r4);
            ListViewofIStmt.getItems().add(ex4.toString());
        } catch (MyException me4) {
            System.err.println("InterruptedException" + me4);
        }

        try{
            MyStack<IStmt> stk5 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl5 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym5=new MyDictionary<String, Value>() ;
            tbl5.push(sym5);
            MyList<Value> out5= new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl5 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer,Value> heap5=new MyHeap<Integer,Value>();
            MyIDictionary<String, Type> dict5 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch5=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck5=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl5=new  MyProcTable<String, ArrayList<String>,IStmt>();
            IStmt ex5=new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("a",new ValuExp(new IntValue(20))),new CompStmt(new ForkStmt(new CompStmt(new HeapAllocStmt("a",new ValuExp(new IntValue(100))),new PrintStmt(new HeapReading(new VarExp("a"))))),new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValuExp(new IntValue(10))),new PrintStmt(new VarExp("v")))))));
            ex5.typecheck(dict5);
            PrgState prg5=new PrgState(stk5,tbl5,out5,filetbl5,heap5,latch5,lck5,prctbl5,ex5);
            Repo r5=new Repo(prg5,"C:\\Users\\testJavaFx\\logfile.txt");
            c5=new Controller(r5);
            ListViewofIStmt.getItems().add(ex5.toString());
        }
        catch (MyException me5) {
            System.err.println("InterruptedException" + me5);
        }

        try{
            MyStack<IStmt> stk6 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl6 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym6=new MyDictionary<String, Value>() ;
            tbl6.push(sym6);
            MyList<Value> out6 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl6 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer,Value> heap6=new MyHeap<Integer,Value>();
            MyIDictionary<String, Type> dict6 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch6=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck6=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl6=new  MyProcTable<String, ArrayList<String>,IStmt>();
            IStmt ex6 = new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new VarDeclStmt("b",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new HeapAllocStmt("a",new ValuExp(new IntValue(0))),new CompStmt(new HeapAllocStmt("b",new ValuExp(new IntValue(0))),new CompStmt(new HeapWriting(new ValuExp(new IntValue(1)),"a"),new CompStmt(new HeapWriting(new ValuExp(new IntValue(2)),"b"),new CompStmt(new AssignmentStmt("v",new RelationalExp("<",new HeapReading(new VarExp("a")),new HeapReading(new VarExp("b"))),new ValuExp(new IntValue(100)),new ValuExp(new IntValue(200))),new CompStmt(new PrintStmt(new VarExp("v")),new CompStmt(new AssignmentStmt("v",new RelationalExp(">",new ArithExp('-',new HeapReading(new VarExp("b")),new ValuExp(new IntValue(2))),new HeapReading(new VarExp("a"))),new ValuExp(new IntValue(100)),new ValuExp(new IntValue(200))),new PrintStmt(new VarExp("v"))))))))))));
            ex6.typecheck(dict6);
            PrgState prg6=new PrgState(stk6,tbl6,out6,filetbl6,heap6,latch6,lck6,prctbl6,ex6);
            Repo r6=new Repo(prg6,"C:\\Users\\testJavaFx\\logfile.txt");
            c6=new Controller(r6);
            ListViewofIStmt.getItems().add(ex6.toString());
        }
        catch (MyException me6) {
            System.err.println("InterruptedException" + me6);
        }

        try{
            MyStack<IStmt> stk7 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl7 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym7=new MyDictionary<String, Value>() ;
            tbl7.push(sym7);
            MyList<Value> out7 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl7 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer,Value> heap7=new MyHeap<Integer,Value>();
            MyIDictionary<String, Type> dict7 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch7=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck7=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl7=new  MyProcTable<String, ArrayList<String>,IStmt>();
            IStmt ex7 = new CompStmt(new CompStmt(new VarDeclStmt("r",new IntType()),new VarDeclStmt("v1",new RefType(new IntType()))),new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v3",new RefType(new IntType())),new CompStmt(new VarDeclStmt("cnt",new IntType()),new CompStmt(new HeapAllocStmt("v1",new ValuExp(new IntValue(2))),new CompStmt(new HeapAllocStmt("v2",new ValuExp(new IntValue(3))),new CompStmt(new HeapAllocStmt("v3",new ValuExp(new IntValue(4))),new CompStmt(new NewLatchStmt("cnt",new HeapReading(new VarExp("v2"))),new CompStmt(new ForkStmt(new CompStmt(new HeapWriting(new ArithExp('*',new HeapReading(new VarExp("v1")),new ValuExp(new IntValue(10))),"v1"),new CompStmt(new PrintStmt(new HeapReading(new VarExp("v1"))),new CompStmt(new CountDownStmt("cnt"),new ForkStmt(new CompStmt(new HeapWriting(new ArithExp('*',new HeapReading(new VarExp("v2")),new ValuExp(new IntValue(10))),"v2"),new CompStmt(new PrintStmt(new HeapReading(new VarExp("v2"))),new CompStmt(new CountDownStmt("cnt"),new ForkStmt(new CompStmt(new HeapWriting(new ArithExp('*',new HeapReading(new VarExp("v3")),new ValuExp(new IntValue(10))),"v3"),new CompStmt(new PrintStmt(new HeapReading(new VarExp("v3"))),new CountDownStmt("cnt")))))))))))),new CompStmt(new AwaitStmt("cnt"),new CompStmt(new PrintStmt(new ValuExp(new IntValue(100))),new CompStmt(new CountDownStmt("cnt"),new PrintStmt(new ValuExp(new IntValue(100)))))))))))))));
            ex7.typecheck(dict7);
            PrgState prg7=new PrgState(stk7,tbl7,out7,filetbl7,heap7,latch7,lck7,prctbl7,ex7);
            Repo r7=new Repo(prg7,"C:\\Users\\testJavaFx\\logfile.txt");
            c7=new Controller(r7);
            ListViewofIStmt.getItems().add(ex7.toString());
        }
        catch (MyException me7) {
            System.err.println("InterruptedException" + me7);
        }
        try{
            MyStack<IStmt> stk8 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl8 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym8=new MyDictionary<String, Value>() ;
            tbl8.push(sym8);
            MyList<Value> out8 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl8 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer,Value> heap8=new MyHeap<Integer,Value>();
            MyIDictionary<String, Type> dict8 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch8=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck8=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl8=new  MyProcTable<String, ArrayList<String>,IStmt>();
            IStmt ex8 = new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),new CompStmt(new VarDeclStmt("x",new IntType()),new CompStmt(new VarDeclStmt("q",new IntType()),new CompStmt(new HeapAllocStmt("v1",new ValuExp(new IntValue(20))),new CompStmt(new HeapAllocStmt("v2",new ValuExp(new IntValue(30))),new CompStmt(new NewLockStmt("x"),new CompStmt(new ForkStmt(new CompStmt(new ForkStmt(new CompStmt(new LockStmt("x"),new CompStmt(new HeapWriting(new ArithExp('-',new HeapReading(new VarExp("v1")),new ValuExp(new IntValue(1))),"v1"),new UnlockStmt("x")))),new CompStmt(new LockStmt("x"),new CompStmt(new HeapWriting(new ArithExp('*',new HeapReading(new VarExp("v1")),new ValuExp(new IntValue(10))),"v1"),new UnlockStmt("x"))))),new CompStmt(new NewLockStmt("q"),new CompStmt(new ForkStmt(new CompStmt(new ForkStmt(new CompStmt(new LockStmt("q"),new CompStmt(new HeapWriting(new ArithExp('+',new HeapReading(new VarExp("v2")),new ValuExp(new IntValue(5))),"v2"),new UnlockStmt("q")))),new CompStmt(new LockStmt("q"),new CompStmt(new HeapWriting(new ArithExp('*',new HeapReading(new VarExp("v2")),new ValuExp(new IntValue(10))),"v2"),new UnlockStmt("q"))))),new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),new CompStmt(new LockStmt("x"),new CompStmt(new PrintStmt(new HeapReading(new VarExp("v1"))),new CompStmt(new UnlockStmt("x"),new CompStmt(new LockStmt("q"),new CompStmt(new PrintStmt(new HeapReading(new VarExp("v2"))),new UnlockStmt("q"))))))))))))))))))));
            ex8.typecheck(dict8);
            PrgState prg8=new PrgState(stk8,tbl8,out8,filetbl8,heap8,latch8,lck8,prctbl8,ex8);
            Repo r8=new Repo(prg8,"C:\\Users\\testJavaFx\\logfile.txt");
            c8=new Controller(r8);
            ListViewofIStmt.getItems().add(ex8.toString());
        }
        catch (MyException me8) {
            System.err.println("InterruptedException" + me8);
        }
        try{
            MyStack<IStmt> stk9 = new MyStack<IStmt>();
            MyIStack<MyIDictionary<String, Value>> tbl9 = new MyStack<MyIDictionary<String, Value>>();
            MyIDictionary<String, Value> sym9=new MyDictionary<String, Value>() ;
            tbl9.push(sym9);
            MyList<Value> out9 = new MyList<Value>();
            MyDictionary<StringValue, BufferedReader> filetbl9 = new MyDictionary<StringValue, BufferedReader>();
            MyHeap<Integer,Value> heap9=new MyHeap<Integer,Value>();
            MyIDictionary<String, Type> dict9 = new MyDictionary<String, Type>();
            MyILatchTable<Integer,Integer> latch9=new MyLatchTable<Integer, Integer>();
            MyLockTable<Integer,Integer> lck9=new MyLockTable<Integer, Integer>();
            MyProcTable<String, ArrayList<String>,IStmt> prctbl1=new  MyProcTable<String, ArrayList<String>,IStmt>();
            ArrayList<String> p= new ArrayList<String>();
            p.add("a");
            p.add("b");
            ArrayList<Exp> p2= new ArrayList<Exp>();
            p2.add(new ArithExp('*',new VarExp("v"),new ValuExp(new IntValue(10))));
            p2.add(new VarExp("w"));
            ArrayList<Exp> p3= new ArrayList<Exp>();
            p3.add(new VarExp("v"));
            p3.add(new VarExp("w"));

            Pair<ArrayList<String>,IStmt> pair=new Pair<>(p,new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ArithExp('+',new VarExp("a"),new VarExp("b"))),new PrintStmt(new VarExp("v")))));
            prctbl1.add("sum",pair);

            Pair<ArrayList<String>,IStmt> pair2=new Pair<>(p, new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ArithExp('*',new VarExp("a"),new VarExp("b"))),new PrintStmt(new VarExp("v")))));
            prctbl1.add("product",pair2);


            IStmt ex9 = new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValuExp(new IntValue(2))),new CompStmt(new VarDeclStmt("w",new IntType()),new CompStmt(new AssignStmt("w",new ValuExp(new IntValue(5))),new CompStmt(new CallProcStmt("sum",p2),new CompStmt(new PrintStmt(new VarExp("v")),new ForkStmt(new CompStmt(new CallProcStmt("product",p3),new ForkStmt(new CallProcStmt("sum",p3))))))))));
            ex9.typecheck(dict9);
            PrgState prg9=new PrgState(stk9,tbl9,out9,filetbl9,heap9,latch9,lck9,prctbl1,ex9);
            Repo r9=new Repo(prg9,"C:\\Users\\testJavaFx\\logfile.txt");
            c9=new Controller(r9);
            ListViewofIStmt.getItems().add(ex9.toString());
        }
        catch (MyException me9) {
            System.err.println("InterruptedException" + me9);
        }
        //ListViewofIStmt.getItems().add("sal");
    }
}
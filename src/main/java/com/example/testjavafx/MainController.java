package com.example.testjavafx;

import com.almasb.fxgl.net.Connection;
import com.example.testjavafx.src.Controller.*;
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
import com.example.testjavafx.src.View.RunCommand;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import com.example.testjavafx.src.View.Interpreter;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements  Initializable{
    Controller controller;
    @FXML
    private TextField NrofPrgStatesTextField;
    @FXML
    private TableView<Map.Entry<Integer, Value>> HeapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>,Integer> HeapAddress;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>,Value> HeapValue;
    @FXML
    private ListView<String> OutListView;
    @FXML
    private ListView<String> FileTableListView;
    @FXML
    private ListView<Integer> PrgStateIdentifiersListView;
    @FXML
    private TableView<Map.Entry<String, Value>> SymTableTableView;
    @FXML
    private TableColumn<Map.Entry<String, Value>,String> SymTblName;

    @FXML
    private TableColumn<Map.Entry<String, Value>,Value> SymTblValue;
    @FXML
    private ListView<String> ExeStackListView;

    @FXML
    private Button RunOneStepBtn;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> LatchTableTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>,Integer> LatchTblLocation;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>,Integer> LatchTblValue;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> LockTableTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>,Integer> LockTableLocation;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>,Integer> LockTableTableValue;

    @FXML
    private TableView<Map.Entry<String,IStmt>> ProcTableTableView;
    @FXML
    private TableColumn<Map.Entry<String,IStmt>,String> ProcNameParamsCol;

    @FXML
    private TableColumn<Map.Entry<String,IStmt>,IStmt> BodyCol;

    private int glb_index=0;
    public void setController(Controller cnt){
        controller = cnt;
        populateProgramStateIdentifiers();
    }
    private void populateProgramStateIdentifiers() {
        List<PrgState> programStates = controller.getRepo().getPrgList();
        PrgStateIdentifiersListView.getItems().clear();
        PrgStateIdentifiersListView.getItems().addAll(controller.getRepo().get_ids());
        PrgStateIdentifiersListView.refresh();
        //programStateListView.setItems(FXCollections.observableList(getProgramStateIds(programStates)));

        NrofPrgStatesTextField.setText("" + programStates.size());
    }
    private List<Integer> getProgramStateIds(List<PrgState> programStateList) {
        return programStateList.stream().map(PrgState::get_id).collect(Collectors.toList());
    }
    PrgState get_prgstate()
    {
        List<PrgState> listprg = controller.getRepo().getPrgList();
        if (listprg.size()>0)
        {
            if (PrgStateIdentifiersListView.getSelectionModel().getSelectedIndex() == -1) {

                if (glb_index>listprg.size()-1)
                {
                    glb_index=0;
                }
                return listprg.get(glb_index);
            }
            int id = PrgStateIdentifiersListView.getSelectionModel().getSelectedIndex();

        /*for (int i = 0; i < listprg.size(); i++) {
                if (id == listprg.get(i).get_id()) {
                    glb_index=i;
                    return listprg.get(i);
                }
        }*/
            if (id<=listprg.size()-1)
            {   glb_index=id;
                return listprg.get(id);}

            else {
                glb_index = 0;
                return listprg.get(0);
            }
        }
        return null;


    }
    @FXML
    protected void executeOneStep()
    {
       /* if (get_prgstate()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute !", ButtonType.OK);
            alert.showAndWait();
        }
        else {*/
            if (controller == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
                alert.showAndWait();
            }

            List<PrgState> programStates = Objects.requireNonNull(controller.getRepo().getPrgList());
            if (programStates.size() > 0) {

                controller.executeOneStep();
                //controller.removeCompletedPrg(controller.getRepo().getPrgList());


                populateExeStack(get_prgstate());
                populateOut(get_prgstate());
                populateFileTable(get_prgstate());
                populateHeapTbl(get_prgstate());
                populateSymTbl(get_prgstate());
                populateLatchTbl(get_prgstate());
                populateLockTbl(get_prgstate());
                populateProcTbl(get_prgstate());


                programStates = controller.removeCompletedPrg(controller.getRepo().getPrgList());
                controller.setProgramStates(programStates);

                populateProgramStateIdentifiers();


            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("An error has occured!");
                alert.setContentText("There is nothing left to execute!");
                alert.showAndWait();
            }
        //}



    }
    @FXML
    protected void updatePrg()
    {
        if (get_prgstate()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "empty", ButtonType.OK);
            alert.showAndWait();
        }
        else {

            populateExeStack(get_prgstate());
            populateOut(get_prgstate());
            populateFileTable(get_prgstate());
            populateHeapTbl(get_prgstate());
            populateSymTbl(get_prgstate());
            populateLatchTbl(get_prgstate());
            populateLockTbl(get_prgstate());
            populateProcTbl(get_prgstate());
        }

    }

    private void test()
    {
        if(controller == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        populateExeStack(controller.getRepo().getPrgList().get(0));
        populateOut(controller.getRepo().getPrgList().get(0));
        populateFileTable(controller.getRepo().getPrgList().get(0));
        populateHeapTbl(controller.getRepo().getPrgList().get(0));
        populateSymTbl(controller.getRepo().getPrgList().get(0));
        /*try {
            controller.oneStepForAllPrg(controller.getRepo().getPrgList());
        }
        catch(InterruptedException ie)
        {
            System.out.println(ie);
        }*/
    }
    private void populateOut(PrgState pg)
    {
        MyIList<Value>out=pg.getList();
        OutListView.getItems().clear();
        OutListView.getItems().addAll(out.tostr());
        OutListView.refresh();
    }
    private void populateFileTable(PrgState pg)
    {
        MyIDictionary<StringValue,BufferedReader>filetbl=pg.getFileTable();
        FileTableListView.getItems().clear();
        FileTableListView.getItems().addAll(filetbl.tostr());
        FileTableListView.refresh();
    }


    protected void populateExeStack(PrgState pg)
    {
        MyIStack<IStmt>exestck=pg.getStk();
        ExeStackListView.getItems().clear();
        ExeStackListView.getItems().addAll(exestck.tostr());
        ExeStackListView.refresh();
    }
    private void populateHeapTbl(PrgState pg)
    {
        HeapTableView.getItems().setAll(getHeapList(pg));
        HeapTableView.refresh();
    }

    private List<Map.Entry<Integer, Value>> getHeapList(PrgState pg){
        MyIHeap<Integer, Value> heapTable = pg.getHeap();
        Collection<Integer> keys=heapTable.getvkeys();
        ArrayList<Map.Entry<Integer, Value>> rez=new ArrayList<Map.Entry<Integer, Value>>();
        Map.Entry<Integer, Value> entry;
        for (int i=0;i<keys.size();i++)
        {
            entry = Map.entry((Integer) keys.toArray()[i],heapTable.lookup((Integer) keys.toArray()[i]));
            rez.add(entry);
        }
        return rez;
    }
    private void populateProcTbl(PrgState pg)
    {
        ProcTableTableView.getItems().setAll(getProcList(pg));
        ProcTableTableView.refresh();
    }
    private List<Map.Entry<String,IStmt>> getProcList(PrgState pg) {
        MyIProcTable<String,ArrayList<String>,IStmt> prcoTbl=pg.getProcTable();
        Collection<String> keys=prcoTbl.getvkeys();
        ArrayList<Map.Entry<String,IStmt>> rez=new ArrayList<Map.Entry<String,IStmt>>();
        Map.Entry<String, IStmt> entry;
        for (int i=0;i<keys.size();i++)
        {
            entry = Map.entry((String)keys.toArray()[i]+prcoTbl.lookup((String) keys.toArray()[i]).getFirst(),prcoTbl.lookup((String) keys.toArray()[i]).getSecond());
            rez.add(entry);


        }
        return rez;
    }
    private void populateSymTbl(PrgState pg)
    {
        SymTableTableView.getItems().setAll(getSymList(pg));
        SymTableTableView.refresh();
    }
    private void populateLatchTbl(PrgState pg)
    {
        LatchTableTableView.getItems().setAll(getLatchList(pg));
        LatchTableTableView.refresh();
    }
    private List<Map.Entry<Integer, Integer>> getLatchList(PrgState pg) {
        MyILatchTable<Integer, Integer> latch = pg.getLatchTable();
        Collection<Integer> keys=latch.getvkeys();
        ArrayList<Map.Entry<Integer, Integer>> rez=new ArrayList<Map.Entry<Integer, Integer>>();
        Map.Entry<Integer, Integer> entry;
        for (int i=0;i<keys.size();i++)
        {
                entry = Map.entry((Integer)keys.toArray()[i],latch.lookup((Integer) keys.toArray()[i]));
                rez.add(entry);
        }
        return rez;
    }
    private List<Map.Entry<String, Value>> getSymList(PrgState pg) {
        MyIDictionary<String, Value> symTbl = pg.getSymTable();
        Collection<String> keys=symTbl.getvkeys();
        ArrayList<Map.Entry<String, Value>> rez=new ArrayList<Map.Entry<String, Value>>();
        Map.Entry<String, Value> entry;
        for (int i=0;i<keys.size();i++)
        {
            try{
            entry = Map.entry((String)keys.toArray()[i],symTbl.lookup((String) keys.toArray()[i]));
            rez.add(entry);

            }catch (MyException me)
            {
                System.out.println("MyException:" +me);
            }
        }
        return rez;
    }
    private void populateLockTbl(PrgState pg)
    {
        LockTableTableView.getItems().setAll(getLockList(pg));
        SymTableTableView.refresh();
    }

    private List<Map.Entry<Integer, Integer>> getLockList(PrgState pg) {
        MyILockTable<Integer, Integer> lockTbl = pg.getLockTable();
        Collection<Integer> keys=lockTbl.getvkeys();
        ArrayList<Map.Entry<Integer, Integer>> rez=new ArrayList<Map.Entry<Integer, Integer>>();
        Map.Entry<Integer, Integer> entry;
        for (int i=0;i<keys.size();i++)
        {
            entry = Map.entry((Integer)keys.toArray()[i],lockTbl.lookup((Integer) keys.toArray()[i]));
            rez.add(entry);

        }
        return rez;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrgStateIdentifiersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //important in order to show things in HeapTableView
        HeapAddress.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        HeapValue.setCellValueFactory(p -> new SimpleObjectProperty<Value>(p.getValue().getValue()));
        //important in order to show things in SymTblTableView
        SymTblName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        SymTblValue.setCellValueFactory(p -> new SimpleObjectProperty<Value>(p.getValue().getValue()));

        LatchTblLocation.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        LatchTblValue.setCellValueFactory(p -> new SimpleObjectProperty<Integer>(p.getValue().getValue()));

        LockTableLocation.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        LockTableTableValue.setCellValueFactory(p -> new SimpleObjectProperty<Integer>(p.getValue().getValue()));

        ProcNameParamsCol.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        BodyCol.setCellValueFactory(p -> new SimpleObjectProperty<IStmt>(p.getValue().getValue()));
    }
}
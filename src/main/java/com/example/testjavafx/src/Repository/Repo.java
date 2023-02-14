package com.example.testjavafx.src.Repository;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Repo implements IRepo{
    List<PrgState> v;
    String logFilePath;
    boolean ok;
    public Repo(PrgState ps, String logfile)
    {
        v = List.of(ps);
        logFilePath=logfile;
        ok=true;
        logFilePath=logfile;
        /*Scanner keyboard = new Scanner(System.in);
        System.out.println("enter a file name");
        String myname = keyboard.nextLine();
        logFilePath="C:\\UBB PROJECTS ANU 2 SEM 1\\MAP\\Lab3_ToyLanguage\\";
        logFilePath+=myname;*/
    }
/*    @Override
    public PrgState getCrtPrg() {
        return v.get(0);
    }*/
    public List<PrgState> getPrgList()
    {
        return v;
    }
    public void setPrgList(List<PrgState> newv)
    {
        v=newv;
    }

    @Override
    public void logPrgStateExec(PrgState p) throws IOException {
        PrintWriter logFile=null;
        if(ok)
        {
            new FileWriter(logFilePath, false).close();
            ok=false;
        }
        try{

            logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.append(p.toString());

        } catch (FileNotFoundException e) {
            System.err.println("Eroare scriere PW "+e);
        }
        finally {
            if(logFile!=null)
                logFile.close();
        }


    }
    public List<Integer> get_ids()
    {
        ArrayList<Integer> ids=new ArrayList<Integer>();
        for (int i=0;i<v.size();i++)
        {
            ids.add(v.get(i).get_id());
        }
        return ids;
    }
}

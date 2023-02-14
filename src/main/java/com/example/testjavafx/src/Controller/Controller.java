package com.example.testjavafx.src.Controller;

import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.PrgState;
import com.example.testjavafx.src.Model.Value.RefValue;
import com.example.testjavafx.src.Model.Value.Value;
import com.example.testjavafx.src.Repository.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepo repo;
    ExecutorService executor;
    boolean first=true;
    public Controller(IRepo r)
    {
        repo=r;

    }
    public IRepo getRepo()
    {
        return repo;
    }
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(p->p.isNotCompleted())
                .collect(Collectors.toList());
    }
    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr,List<Integer> heapAddr ,Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
                //.filter(e->symTableAddr.contains(e.getKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddres();})
                .collect(Collectors.toList());
    }
    List<Integer> getAddrFromHeap(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddres();})
                .collect(Collectors.toList());
    }
    public void executeOneStep()
    {
        executor = Executors.newFixedThreadPool(7);
        //repo.setPrgList(removeCompletedPrg(repo.getPrgList()));
        List<PrgState> programStates = repo.getPrgList();
        List<Value> allsytbl=new Vector<Value>();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllPrg(repo.getPrgList());
            } catch (InterruptedException e) {
                System.out.println();
            }
            for(int i=0;i<programStates.size();i++)
            {
                allsytbl.addAll(programStates.get(i).getSymTable().getvalues());
            }
            programStates.get(0).getHeap().setContent(safeGarbageCollector(
                    getAddrFromSymTable(allsytbl),getAddrFromHeap(programStates.get(0).getHeap().getvalues()),
                    programStates.get(0).getHeap().retall()));
            /*programStates.forEach(e -> {
                try {
                    repo.logPrgStateExec(e);
                } catch (IOException e1) {
                    System.out.println();
                }
            });*/
            //repo.setPrgList(removeCompletedPrg(repo.getPrgList()));
            executor.shutdownNow();
        }
    }
    public void setProgramStates(List<PrgState> programStates) {
        this.repo.setPrgList(programStates);
    }
    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        boolean check=false;
        //before the execution, print the PrgState List into the log file
        if (first)
        {   prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
            first=false;
    }

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = new ArrayList<PrgState>();
        newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException se) {
                        System.err.println("InterruptedException" + se);
                    }

                    return null;
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());
        List<PrgState> l = new ArrayList<PrgState>(prgList);
        if (newPrgList.size()>0)
        {
            //l e folosit ca List nu se poate modifica dar ArrayList da
            //Also ca sa printeze 100%corect in logfile use: removeCompletedPrg
            l.addAll(newPrgList);
            check=true;

        }
          //  prgList.addAll(newPrgList);
          //  prgList.addAll(newPrgList);
        if(!check) {
       prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

            repo.setPrgList(prgList);
        }
        else
        {
            l.forEach(prg -> {
                try {
                    repo.logPrgStateExec(prg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            repo.setPrgList(l);
            check=false;
        }
    }

    public void allStep() throws InterruptedException {
        executor= Executors.newFixedThreadPool(2);
        int i=0;
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        List<Value> allsytbl=new Vector<Value>();
        while(prgList.size()>0)
        {
            for(i=0;i<prgList.size();i++)
            {
                allsytbl.addAll(prgList.get(i).getSymTable().getvalues());
            }
            prgList.get(0).getHeap().setContent(safeGarbageCollector(
                getAddrFromSymTable(allsytbl),getAddrFromHeap(prgList.get(0).getHeap().getvalues()),
                    prgList.get(0).getHeap().retall()));


            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repo.getPrgList());
        }

        executor.shutdownNow();

        repo.setPrgList(prgList);


    }
}

package com.example.testjavafx.src.Repository;
import com.example.testjavafx.src.Exception.*;
import com.example.testjavafx.src.Model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepo {
  //  PrgState getCrtPrg();

    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newv);
    void logPrgStateExec(PrgState p) throws IOException;
    List<Integer> get_ids();
}

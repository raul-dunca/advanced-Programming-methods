package com.example.testjavafx.src.View;

import com.example.testjavafx.src.Controller.Controller;
import com.example.testjavafx.src.Exception.*;

public class RunCommand extends Command{
    Controller ctrl;
    RunCommand(String key,String desc,Controller c)
    {
        super(key,desc);
        ctrl=c;
    }
    @Override
    public void execute() {
        try {
            ctrl.allStep();
        }
         catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

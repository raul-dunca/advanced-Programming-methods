package com.example.testjavafx.src.View;

public abstract class Command {
    private String key;
    private String description;
    public Command(String k,String des)
    {
        key=k;
        description=des;
    }
    public abstract void execute();
    public String getKey()
    {
        return key;
    }
    public String getDescription()
    {
        return description;
    }
}

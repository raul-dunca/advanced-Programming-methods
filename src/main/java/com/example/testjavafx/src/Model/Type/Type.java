package com.example.testjavafx.src.Model.Type;

import com.example.testjavafx.src.Model.Value.Value;

public interface Type {
    public boolean equals(Object a);
    public Value defaultvalue();
    public Type deepcopy();
    public String toString();

}

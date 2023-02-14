package com.example.testjavafx.src.Model.Value;

import com.example.testjavafx.src.Model.Type.Type;

public interface Value {
    boolean equals(Object a);
    Type getType();

    public Value deepcopy();

    public String toString();
}

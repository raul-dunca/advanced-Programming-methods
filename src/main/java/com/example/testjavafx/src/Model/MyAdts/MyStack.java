package com.example.testjavafx.src.Model.MyAdts;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {

    Stack<T> stack=new Stack<T>();

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isempty() {
        return stack.isEmpty();
    }

    @Override
    public String tostr() {
        String s=new String();
        Stack<T> copystack=new Stack<T>();
        while (!stack.empty())
        {
            T st=stack.pop();
            s+=st.toString()+"\n";
            copystack.push(st);
        }
        while (!copystack.empty())
        {
            T st=copystack.pop();
            stack.push(st);
        }
        return s;
    }
    @Override
    public T top() {

        return stack.peek();
    }
}

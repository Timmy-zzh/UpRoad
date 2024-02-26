package com.timmy.javabasic.generics;

public class Genertor<T> {

    private T data;

    public Genertor() {
    }

    public Genertor(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

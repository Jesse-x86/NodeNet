package org.techaurora.nodenet.utils;

public class VarBox<T>{
    private T obj;

    public VarBox(T obj){
        setObj(obj);
    }

    public void setObj(T obj){
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }
}

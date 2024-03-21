package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public abstract class AbstractInputHandler implements InputHandler {

    protected Node node;
    protected Method m;
    protected Parameter[] parameters;
    protected boolean isStatic;

    public AbstractInputHandler(){

    }

    public void init(Node node){
        this.node = node;
        String methodName = node.getMethodName();
        for(Method d : node.getClass().getMethods()){
            if(d.getName().equals(methodName)){
                m = d;
                parameters = m.getParameters();
                break;
            }
        }
        if(null == m){
            throw new IllegalArgumentException("Class " + node.getClass().getName() + " have a bad Method Name definition.");
        }
    }

    abstract public void insert(Object input, int index);
    public boolean isStatic(){
        return isStatic;
    }

    abstract public void checkAvaliability();

}

package org.techaurora.nodenet.utils;

import java.util.List;

public abstract class AbstractInputHandler implements InputHandler {

    List<Class<?>> types;
    List objects;
    public AbstractInputHandler(List<Class<?>> types){
        this.types = types;
    }

    public void insert(Object input, int index){
        if(types.get(index).isInstance(input)){
            VarBox vb = new VarBox(input);

        }
    }
    /**
     *
     * @return True if all input that are not null-able are available
     * False if not all input are available
     */
    public boolean avaliable(){
        return true;
    }




}

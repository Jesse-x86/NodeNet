package org.techaurora.nodenet.utils;

public class DefaultInputHandler extends AbstractInputHandler{
    public DefaultInputHandler() {
        super();
    }

    @Override
    public void insert(Object input, int index){
        if(index < 0 || index >= parameters.length){
            throw new IndexOutOfBoundsException("Given argument index out of bound @ " + this.getClass().getName());
        }
        if(parameters[index].getType().isInstance(input)){
            VarBox vb = new VarBox(input);

        }
    }

    @Override
    public boolean isAvaliable() {
        return false;
    }

    @Override
    public void invoke() {

    }
}

package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.settings.Validator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractInputHandler implements InputHandler {
    protected Node node;
    protected List<Object> cache;
    protected List<Boolean> isPersistent;


    public AbstractInputHandler(){

    }

    @Override
    public void init(Node node){
        this.node = node;
        cache = new ArrayList<>();
        isPersistent = new ArrayList<>();
        for(int i = 0; i < this.node.getInputTypes().size(); i++) {
            cache.add(null);
            isPersistent.add(false);
        }
    }

//    public void input(int index, Object input){
//        input(index, input, false);
//    }

    @Override
    public void input(int index, Object input, boolean isPersistent){
        if(null == input || node.getInputType(index).isInstance(input)) {
            cache.set(index, input);
            this.isPersistent.set(index, isPersistent);
        } else{
            throw new IllegalArgumentException("Illegal Input. Demand " + node.getInputType(index).getName() + ", got " + input.getClass().getName());
        }
    }

    @Override
    public boolean isAvaliable(){
        List<Validator> validators = node.getInputValidators();
        for(int i = 0; i < cache.size(); i++){
            if((null != validators.get(i) && !validators.get(i).validate(cache.get(i)))
                            || (null == validators.get(i) && null == cache.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Object> provide(){
        List<Object> _c = new ArrayList<>();
        for(int i = 0; i < cache.size(); i++){
            _c.add(cache.get(i));
            if(!isPersistent.get(i)){
                cache.set(i, null);
            }
        }
        return _c;
    }
    @Override
    public List<Object> provideCopy(){
        return List.copyOf(cache);
    }
    @Override
    public List<Object> provideAndEmpty(){
        List<Object> _c = cache;
        init(node);
        return _c;
    }

}

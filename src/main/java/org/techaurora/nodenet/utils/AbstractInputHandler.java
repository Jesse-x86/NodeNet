package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.settings.Validator;

import java.util.*;

public abstract class AbstractInputHandler implements InputHandler {
    protected Node node;
    protected Map<String, InputStorageObject> cache;

    @Override
    public void init(Node node){
        this.node = node;
        if(null == cache)
            cache = new HashMap<>();
    }

    @Override
    public void input(String inputID, Object inputObj, boolean isPersistent){
        if(null == inputObj || node.getInputType(inputID).isInstance(inputObj)) {
            cache.put(inputID, new InputStorageObject(inputObj, isPersistent));
        } else{
//            throw new IllegalArgumentException("Illegal Input. Demand " + node.getInputType(index).getName() + ", got " + input.getClass().getName());
        }
    }

    @Override
    public boolean isAvailable(){
        Map<String, Node.IOTypeValidateObject> validators = node.getInputValidateObjs();
        // if any didn't pass validation, this is not available, otherwise this is
        for(String s : validators.keySet()){
            if(!validators.get(s).validate(cache.get(s).object)) return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> provide(){
        Map<String, Object> objectMap = new HashMap<>();
        for(String s: cache.keySet()){
            var _c = cache.get(s);
            objectMap.put(s, _c.object);
            if(!_c.isPersistent){
                cache.remove(s);
            }
        }
        return objectMap;
    }

    @Override
    public Map<String, Object> provideAndEmpty(){
        try{
            return provide();
        } finally {
            cache.clear();
        }
    }

}

package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.*;

public abstract class AbstractInputHandler implements IInputHandler {
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
            // if cache's input "s" is null then check if null is okay
            InputStorageObject inputStorageObject = cache.get(s);
            Object obj = inputStorageObject == null ? null : inputStorageObject.object;
            if(!validators.get(s).validate(obj)) return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> provide(){
        // return a new map
        Map<String, Object> objectMap = new HashMap<>();
        // store keys to delete so we don't mess sets up
        ArrayDeque<String> toDel = new ArrayDeque<>();

        for(Map.Entry<String, InputStorageObject> entry : cache.entrySet()){

            objectMap.put(entry.getKey(), entry.getValue().object);

            if(!entry.getValue().isPersistent){
                toDel.add(entry.getKey());
            }
        }
        // remove non-persistent inputs
        for(String s : toDel) cache.remove(s);
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

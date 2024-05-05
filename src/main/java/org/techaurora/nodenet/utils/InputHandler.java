package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class InputHandler extends AbstractInputHandler{
    protected Node node;
    protected Map<String, InputStorageObject> cache;

    public void init(Node node){
        this.node = node;
        if(null == cache)
            cache = new HashMap<>();
    }

    public void input(String inputID, Object inputObj, boolean isPersistent){
        if(null == inputObj || node.getInputType(inputID).isInstance(inputObj)) {
            cache.put(inputID, new InputStorageObject(inputObj, isPersistent));
        } else{
//            throw new IllegalArgumentException("Illegal Input. Demand " + node.getInputType(index).getName() + ", got " + input.getClass().getName());
        }
    }

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

    public Map<String, Object> provideAndEmpty(){
        try{
            return provide();
        } finally {
            cache.clear();
        }
    }

    class InputStorageObject {
        public Object object;
        public Boolean isPersistent;

        public InputStorageObject(Object object, Boolean isPersistent){
            this.object = object;
            this.isPersistent = isPersistent;
        }

        @Override
        public boolean equals(Object obj) {
            if(InputStorageObject.class.isInstance(obj)){
                return object.equals(((InputStorageObject) obj).object) && isPersistent.equals(((InputStorageObject) obj).isPersistent);
            }
            return super.equals(obj);
        }
    }
}

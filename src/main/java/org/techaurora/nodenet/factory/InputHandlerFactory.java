package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.utils.IInputHandler;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class InputHandlerFactory implements Factory{
    Map<String, Class<? extends IInputHandler>> data;

    @Override
    public void init(Map<String, Class<?>> classSet) {
        if(null == data) data = new HashMap<>();
        for(String k : classSet.keySet()){
            Class<?> clazz = classSet.get(k);
            if(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())){
                continue;
            }
            if(IInputHandler.class.isAssignableFrom(clazz)){
                data.put(k, clazz.asSubclass(IInputHandler.class));
            }
        }
    }

    @Override
    public IInputHandler build(String className) {
        if(data.get(className) == null) return null;
        try {
            return data.get(className).getConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

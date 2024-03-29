package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class OutputHandlerFactory implements Factory{
    Map<String, Class<? extends OutputHandler>> data;
    /**
     * @param classSet
     */
    @Override
    public void init(Map<String, Class<?>> classSet) {
        if(null == data) data = new HashMap<>();
        for(String k : classSet.keySet()){
            Class<?> clazz = classSet.get(k);
            if(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())){
                continue;
            }
            if(OutputHandler.class.isAssignableFrom(clazz)){
                data.put(k, clazz.asSubclass(OutputHandler.class));
            }
        }
    }

    /**
     * @param className
     * @return
     */
    @Override
    public OutputHandler build(String className) {
        if(data.get(className) == null) return null;
        try {
            return data.get(className).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

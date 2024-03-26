package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.utils.InputHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class InputHandlerFactory implements Factory{
    Map<String, Class<? extends InputHandler>> data;
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
            if(InputHandler.class.isAssignableFrom(clazz)){
                data.put(k, clazz.asSubclass(InputHandler.class));
            }
        }
    }

    /**
     * @param className
     * @return
     */
    @Override
    public InputHandler build(String className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(data.get(className) == null) return null;
        return data.get(className).getConstructor().newInstance();
    }
}

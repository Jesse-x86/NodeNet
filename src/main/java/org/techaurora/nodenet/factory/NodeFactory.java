package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.nodes.Node;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class NodeFactory implements Factory {
    Map<String, Class<? extends Node>> data;
    /**
     * @param classSet
     */
    @Override
    public void init(Map<String, Class<?>> classSet){
        data = (Map<String, Class<? extends Node>>) classSet;
    }

    /**
     * @param className
     * @return
     */
    @Override
    public Node build(String className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(data.get(className) == null) return null;
        return data.get(className).getConstructor().newInstance();
    }
}

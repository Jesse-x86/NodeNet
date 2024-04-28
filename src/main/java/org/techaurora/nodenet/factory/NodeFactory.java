package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.nodes.Node;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class NodeFactory implements Factory {
    Map<String, Class<? extends Node>> data;

    @Override
    public void init(Map<String, Class<?>> classSet) {
        if(null == data) data = new HashMap<>();
        for(String k : classSet.keySet()){
            Class<?> clazz = classSet.get(k);
            if(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())){
                continue;
            }
            if(Node.class.isAssignableFrom(clazz)){
                data.put(k, clazz.asSubclass(Node.class));
            }
        }
    }

    @Override
    public Node build(String className) {
        if(data.get(className) == null) return null;
        try {
            return data.get(className).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

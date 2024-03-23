package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.settings.SettingsHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class SettingsHandlerFactory implements Factory{
    Map<String, Class<? extends SettingsHandler>> data;
    /**
     * @param classSet
     */
    @Override
    public void init(Map<String, Class<?>> classSet){
        data = (Map<String, Class<? extends SettingsHandler>>) classSet;
    }

    /**
     * @param className
     * @return
     */
    @Override
    public SettingsHandler build(String className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(data.get(className) == null) return null;
        return data.get(className).getConstructor().newInstance();
    }
}

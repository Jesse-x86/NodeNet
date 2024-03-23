package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.settings.Settings;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class SettingsFactory implements Factory{
    Map<String, Class<? extends Settings>> data;
    /**
     * @param classSet
     */
    @Override
    public void init(Map<String, Class<?>> classSet){
        data = (Map<String, Class<? extends Settings>>) classSet;
    }

    /**
     * @param className
     * @return
     */
    @Override
    public Settings build(String className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(data.get(className) == null) return null;
        return data.get(className).getConstructor().newInstance();
    }
}

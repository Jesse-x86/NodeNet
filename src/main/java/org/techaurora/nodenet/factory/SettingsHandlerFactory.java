package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.settings.ISettingsHandler;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class SettingsHandlerFactory implements Factory{
    Map<String, Class<? extends ISettingsHandler>> data;

    @Override
    public void init(Map<String, Class<?>> classSet) {
        if(null == data) data = new HashMap<>();
        for(String k : classSet.keySet()){
            Class<?> clazz = classSet.get(k);
            if(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())){
                continue;
            }
            if(ISettingsHandler.class.isAssignableFrom(clazz)){
                data.put(k, clazz.asSubclass(ISettingsHandler.class));
            }
        }
    }

    @Override
    public ISettingsHandler build(String className) {
        if(data.get(className) == null) return null;
        try {
            return data.get(className).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

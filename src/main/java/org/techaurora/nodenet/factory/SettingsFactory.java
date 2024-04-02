package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.settings.Settings;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class SettingsFactory implements Factory{
    Map<String, Class<? extends Settings>> data;

    @Override
    public void init(Map<String, Class<?>> classSet) {
        if(null == data) data = new HashMap<>();
        for(String k : classSet.keySet()){
            Class<?> clazz = classSet.get(k);
            if(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())){
                continue;
            }
            if(Settings.class.isAssignableFrom(clazz)){
                data.put(k, clazz.asSubclass(Settings.class));
            }
        }
    }

    @Override
    public Settings build(String className) {
        if(data.get(className) == null) return null;
        try {
            return data.get(className).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

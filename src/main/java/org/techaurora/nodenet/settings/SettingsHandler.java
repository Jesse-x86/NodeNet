package org.techaurora.nodenet.settings;

import java.util.List;

public interface SettingsHandler {
//    public void init(List<String> names, List<Class<?>> types);
//    public void init(List<String> names, List<Class<?>> types, List<Object> values);
    public void init(List<String> names, List<Class<?>> types, List<Object> values, List<Validator> validators);
}

package org.techaurora.nodenet.settings;

import java.util.List;

public interface SettingsHandler {
//    public void init(List<String> names, List<Class<?>> types);
//    public void init(List<String> names, List<Class<?>> types, List<Object> values);
    public void init(List<Settings> settings);
    public Settings getSettings(int index);
    public void setSettings(int index, Settings settings);

    public Object getSettingsValue(int index);
    public void setSettingsValue(int index, Object value);
}

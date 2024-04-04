package org.techaurora.nodenet.settings;

import org.techaurora.nodenet.nodes.Node;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSettingsHandler implements SettingsHandler{
    Node node;
    Map<String, Settings> settingsMap;

    @Override
    public void init(Node node){
        this.node = node;
        if(settingsMap == null) {
            this.settingsMap = new HashMap<>();
        }
    }

    @Override
    public Map<String, Settings> getSettingsMap(){
        return settingsMap;
    }

    @Override
    public void setSettingsMap(Map<String, Settings> settingsMap){
        for (Settings settings : settingsMap.values()) {
            setSettings(settings);
        }
    }

    @Override
    public Settings getSettings(String settingsID){
        return settingsMap.get(settingsID);
    }
    @Override
    public void setSettings(Settings settings){
        // only put settings in if settings value makes sense
        if(settings.validate(settings.getValue())) {
            settingsMap.put(settings.getName(), settings);
        }
    }
    @Override
    public boolean hasSettings(String settingsID) {
        return settingsMap.containsKey(settingsID);
    }
    @Override
    public Settings removeSettings(String settingsID){
        return settingsMap.remove(settingsID);
    }
    @Override
    public Object getSettingsValue(String settingsID) {
        var settings = settingsMap.get(settingsID);
        if(null == settings) return null;
        return settings.getValue();
    }
    @Override
    public void setSettingsValue(String settingsID, Object value){
        var settings = settingsMap.get(settingsID);
        if(null != settings && settings.validate(value)) {
            settings.setValue(value);
        }
    }
}

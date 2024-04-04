package org.techaurora.nodenet.settings;

import org.techaurora.nodenet.nodes.Node;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSettingsHandler implements SettingsHandler{
    Node node;
    Map<String, Settings> settingsMap;

    public void init(Node node){
        this.node = node;
        if(settingsMap == null) {
            this.settingsMap = new HashMap<>();
        }
    }

    public Map<String, Settings> getSettingsMap(){
        return settingsMap;
    }

    public void setSettings(Map<String, Settings> settingsMap){
        this.settingsMap.putAll(settingsMap);
    }

    public Settings getSettings(String settingsID){
        return settingsMap.get(settingsID);
    }
    public void setSettings(String settingsID, Settings settings){
        if(settings.validate(settings.getValue())) {
            settingsMap.put(settingsID, settings);
        }
    }

    public Settings removeSettings(String index){
        return settingsMap.remove(index);
    }

    public Object getSettingsValue(String settingsID){
        return settingsMap.get(settingsID).getValue();
    }
    public void setSettingsValue(String settingsID, Object value){
        settingsMap.get(settingsID).setValue(value);
    }
}

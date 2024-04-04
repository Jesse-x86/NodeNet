package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Settings;
import org.techaurora.nodenet.settings.SettingsHandler;

import java.util.List;
import java.util.Map;

public abstract class AbstractNodeWithSettings extends AbstractNode implements NodeWithSettings{
    protected SettingsHandler settingsHandler;

    /**
     * Used to store a list of settings
     * If you don't initialize the settings list, you probably don't need to extend this Node
     * , try to extend AbstractNode instead.
     */
    protected Map<String, Settings> defaultSettings;

    public NodeWithSettings setSettingsHandler(SettingsHandler settingsHandler){
        this.settingsHandler = settingsHandler;
        this.settingsHandler.init(this);
        if(settingsHandler.getSettingsMap().size() == 0){
            this.settingsHandler.setSettingsMap(defaultSettings);
        }
        return this;
    }
    public Settings getSettings(String settingsID){
        return settingsHandler.getSettings(settingsID);
    }
    public void setSettings(String settingsID, Settings settings){
        settingsHandler.setSettingsValue(settingsID, settings);
    }
    public Settings removeSettings(String settingsID){
        return this.settingsHandler.removeSettings(settingsID);
    }


    public Object getSettingsValue(String settingsID){
        return this.settingsHandler.getSettingsValue(settingsID);
    }
    public void setSettingsValue(String settingsID, Object settingsObj){
        this.settingsHandler.setSettingsValue(settingsID, settingsObj);
    }
}

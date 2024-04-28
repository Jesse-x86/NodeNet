package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.ISettings;
import org.techaurora.nodenet.settings.ISettingsHandler;

import java.util.Map;

public abstract class AbstractNodeWithSettings extends AbstractNode implements NodeWithSettings{
    protected ISettingsHandler settingsHandler;

    /**
     * Used to store a list of settings
     * If you don't initialize the settings list, you probably don't need to extend this Node
     * , try to extend AbstractNode instead.
     */
    protected Map<String, ISettings> defaultSettings;

    @Override
    public NodeWithSettings setSettingsHandler(ISettingsHandler settingsHandler){
        this.settingsHandler = settingsHandler;
        this.settingsHandler.init(this);
        if(settingsHandler.getSettingsMap().size() == 0){
            this.settingsHandler.setSettingsMap(defaultSettings);
        }
        return this;
    }
    @Override
    public ISettings getSettings(String settingsID){
        if(settingsHandler == null) return null;
        return settingsHandler.getSettings(settingsID);
    }
    @Override
    public void setSettings(ISettings settings){

        this.settingsHandler.setSettings(settings);
    }
    @Override
    public ISettings removeSettings(String settingsID){
        return this.settingsHandler.removeSettings(settingsID);
    }

    @Override
    public Object getSettingsValue(String settingsID){
        return this.settingsHandler.getSettingsValue(settingsID);
    }
    @Override
    public void setSettingsValue(String settingsID, Object settingsObj){
        this.settingsHandler.setSettingsValue(settingsID, settingsObj);
    }
}

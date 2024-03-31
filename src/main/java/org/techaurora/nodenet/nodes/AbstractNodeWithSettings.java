package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Settings;
import org.techaurora.nodenet.settings.SettingsHandler;

import java.util.List;

public abstract class AbstractNodeWithSettings extends AbstractNode implements NodeWithSettings{
    protected SettingsHandler settingsHandler;

    /**
     * Used to store a list of settings
     * If you don't initialize the settings list, you probably don't need to extend this Node
     * , try to extend AbstractNode instead.
     */
    protected List<Settings> settings;

    public void settingsInit(List<Settings> settings) {
        this.settings = settings;
    }
    public void setSettingsHandler(SettingsHandler settingsHandler){
        this.settingsHandler = settingsHandler;
        this.settingsHandler.init(settings);
    }
    public Settings settingsGet(int index){
        return settingsHandler.getSettings(index);
    }
    public void settingsSet(int index, Settings settings){
        settingsHandler.setSettingsValue(index, settings);
    }
}

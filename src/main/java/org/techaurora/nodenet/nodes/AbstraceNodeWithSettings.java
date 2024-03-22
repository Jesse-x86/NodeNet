package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Settings;
import org.techaurora.nodenet.settings.SettingsHandler;

import java.util.List;

public abstract class AbstraceNodeWithSettings extends AbstractNode implements NodeWithSettings{
    protected SettingsHandler settingsHandler;
    protected static List<Settings> settings;
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

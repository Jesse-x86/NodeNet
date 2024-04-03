package org.techaurora.nodenet.settings;

import java.util.List;

public abstract class AbstractSettingsHandler implements SettingsHandler{
    List<Settings> settingsList;

    public void init(List<Settings> settingsList){
        this.settingsList = settingsList;
    }

    public Settings getSettings(int index){
        return settingsList.get(index);
    }
    public void setSettings(int index, Settings settings){
        if(settings.validate(settings.getValue())) {
            settingsList.set(index, settings);
        }
    }

    public Object getSettingsValue(int index){
        return settingsList.get(index).getValue();
    }
    public void setSettingsValue(int index, Object value){
        settingsList.get(index).setValue(value);
    }
}

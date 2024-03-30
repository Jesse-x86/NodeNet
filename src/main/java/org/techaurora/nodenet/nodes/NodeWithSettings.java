package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Settings;
import org.techaurora.nodenet.settings.SettingsHandler;

import java.util.List;

public interface NodeWithSettings extends Node{
    /**
     * Initialize the settings, usually include all necessary settings already, with some exceptions
     * @param settings set the settings of current Node
     */
    public void settingsInit(List<Settings> settings);
    public void setSettingsHandler(SettingsHandler settingsHandler);

    /**
     * get Settings at index.
     * @param index
     * @return
     */
    public Settings settingsGet(int index);
    public void settingsSet(int index, Settings settings);
}

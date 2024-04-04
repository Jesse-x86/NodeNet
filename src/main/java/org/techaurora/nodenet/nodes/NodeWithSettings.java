package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Settings;
import org.techaurora.nodenet.settings.SettingsHandler;

public interface NodeWithSettings extends Node{
    /**
     *
     * @param settingsHandler
     * @return
     */
    public NodeWithSettings setSettingsHandler(SettingsHandler settingsHandler);

    /**
     *
     * @param settingsID
     * @return
     */
    public Settings getSettings(String settingsID);
    public void setSettings(String settingsID, Settings settings);
    public Settings removeSettings(String settingsID);

    public Object getSettingsValue(String settingsID);
    public void setSettingsValue(String settingsID, Object settingsObj);
}

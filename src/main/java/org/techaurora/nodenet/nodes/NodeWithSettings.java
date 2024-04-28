package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.ISettings;
import org.techaurora.nodenet.settings.ISettingsHandler;

public interface NodeWithSettings extends Node{
    /**
     *
     * @param settingsHandler
     * @return
     */
    public NodeWithSettings setSettingsHandler(ISettingsHandler settingsHandler);

    /**
     *
     * @param settingsID
     * @return
     */
    public ISettings getSettings(String settingsID);
    public void setSettings(ISettings settings);
    public ISettings removeSettings(String settingsID);

    public Object getSettingsValue(String settingsID);
    public void setSettingsValue(String settingsID, Object settingsObj);
}

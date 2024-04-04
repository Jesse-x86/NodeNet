package org.techaurora.nodenet.settings;

import org.techaurora.nodenet.nodes.Node;

import java.util.Map;

public interface SettingsHandler {
    public void init(Node node);
    public Settings getSettings(String settingsID);

    public Map<String, Settings> getSettingsMap();
    public void setSettingsMap(Map<String, Settings> settingsMap);

    /**
     * update the Settings object for certain
     * @param settingsID
     * @param settings
     */
    public void setSettings(String settingsID, Settings settings);
    public Settings removeSettings(String index);

    public Object getSettingsValue(String settingsID);
    public void setSettingsValue(String settingsID, Object value);
}

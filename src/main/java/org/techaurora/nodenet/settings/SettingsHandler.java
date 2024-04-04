package org.techaurora.nodenet.settings;

import org.techaurora.nodenet.nodes.Node;

import java.util.Map;

public interface SettingsHandler {
    public void init(Node node);

    public Map<String, Settings> getSettingsMap();
    public void setSettingsMap(Map<String, Settings> settingsMap);

    /**
     * update the Settings object
     * @param settings
     */
    public void setSettings(Settings settings);
    public Settings getSettings(String settingsID);
    public Settings removeSettings(String settingsID);
    public boolean hasSettings(String settingsID);

    public Object getSettingsValue(String settingsID);
    public void setSettingsValue(String settingsID, Object value);
}

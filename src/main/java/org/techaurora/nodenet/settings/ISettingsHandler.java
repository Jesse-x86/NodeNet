package org.techaurora.nodenet.settings;

import org.techaurora.nodenet.nodes.Node;

import java.util.Map;

public interface ISettingsHandler {
    public void init(Node node);

    public Map<String, ISettings> getSettingsMap();
    public void setSettingsMap(Map<String, ISettings> settingsMap);

    /**
     * update the Settings object
     * @param settings
     */
    public void setSettings(ISettings settings);
    public ISettings getSettings(String settingsID);
    public ISettings removeSettings(String settingsID);
    public boolean hasSettings(String settingsID);

    public Object getSettingsValue(String settingsID);
    public void setSettingsValue(String settingsID, Object value);
}

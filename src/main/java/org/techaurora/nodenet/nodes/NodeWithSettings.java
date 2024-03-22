package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Settings;
import org.techaurora.nodenet.settings.SettingsHandler;

public interface NodeWithSettings extends Node{
    public void setSettingsHandler(SettingsHandler settingsHandler);
    public Settings settingsGet(int index);
    public void settingsSet(int index, Settings settings);
}

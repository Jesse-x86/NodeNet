package org.techaurora.nodenet.loader;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.settings.Settings;
import org.techaurora.nodenet.settings.SettingsHandler;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;

import java.io.IOException;
import java.util.Map;

/**
 * This class is used for automatically locate and load
 * custom Nodes into system to register them
 */
public interface NodeLoader {
    public void loadFromFolder(String dir) throws IOException;
    public void loadFromFolder(String dir, int depth) throws IOException;
    public Map<String, Class<?>> getAllModClasses();
    public Map<String, Class<? extends Node>> getNodes();
    public Map<String, Class<? extends InputHandler>> getInputHandlers();
    public Map<String, Class<? extends OutputHandler>> getOutputHandlers();
    public Map<String, Class<? extends SettingsHandler>> getSettingsHandlers();
    public Map<String, Class<? extends Settings>> getSettings();
}

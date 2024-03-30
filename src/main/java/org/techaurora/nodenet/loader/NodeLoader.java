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
    /**
     * Load mod into Maps from folder with depth = 0
     * @see #loadFromFolder(String, int)
     * @param dir the folder to be used to load mods
     * @throws IOException in case weird things happens, like file in use.
     */
    public void loadFromFolder(String dir) throws IOException;

    /**
     * Load mod into Maps from folder, will recursively search in depth layers, 0 = search current folder only.
     * @param dir the folder to be used to load mods
     * @param depth the depth to recursively search
     * @throws IOException in case weird things happens, like file in use.
     */
    public void loadFromFolder(String dir, int depth) throws IOException;

    /**
     *
     * @return A map of class name and all classes
     */
    public Map<String, Class<?>> getAllModClasses();

    /**
     *
     * @return A map of class name and Node classes
     */
    public Map<String, Class<? extends Node>> getNodes();

    /**
     *
     * @return A map of class name and InputHandler classes
     */
    public Map<String, Class<? extends InputHandler>> getInputHandlers();

    /**
     *
     * @return A map of class name and OutputHandler classes
     */
    public Map<String, Class<? extends OutputHandler>> getOutputHandlers();

    /**
     *
     * @return A map of class name and SettingsHandler classes
     */
    public Map<String, Class<? extends SettingsHandler>> getSettingsHandlers();

    /**
     *
     * @return A map of class name and Settings classes
     */
    public Map<String, Class<? extends Settings>> getSettings();
}

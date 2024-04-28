package org.techaurora.nodenet.loader;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.settings.ISettings;
import org.techaurora.nodenet.settings.ISettingsHandler;
import org.techaurora.nodenet.utils.IInputHandler;
import org.techaurora.nodenet.utils.IOutputHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class AbstractNodeLoader implements NodeLoader {
    protected Map<String, Class<?>> allModClasses;
    protected Map<String, Class<? extends Node>> nodes;
    protected Map<String, Class<? extends IInputHandler>> inputHandlers;
    protected Map<String, Class<? extends IOutputHandler>> outputHandlers;
    protected Map<String, Class<? extends ISettingsHandler>> settingsHandlers;
    protected Map<String, Class<? extends ISettings>> settings;

    public Map<String, Class<?>> getAllModClasses(){
        return allModClasses;
    }
    public Map<String, Class<? extends Node>> getNodes(){
        return nodes;
    }
    public Map<String, Class<? extends IInputHandler>> getInputHandlers(){
        return inputHandlers;
    }
    public Map<String, Class<? extends IOutputHandler>> getOutputHandlers(){
        return outputHandlers;
    }
    public Map<String, Class<? extends ISettingsHandler>> getSettingsHandlers(){
        return settingsHandlers;
    }
    public Map<String, Class<? extends ISettings>> getSettings(){
        return settings;
    }



    /**
     * @param dir
     */
    @Override
    public void loadFromFolder(String dir) throws IOException {
        loadFromFolder(dir, 0);
    }

    @Override
    public void loadFromFolder(String dir, int depth) throws IOException {
        if(allModClasses == null) {
            allModClasses = new HashMap<>();
        }
        if(nodes == null) {
            nodes = new HashMap<>();
        }
        if(inputHandlers == null) {
            inputHandlers = new HashMap<>();
        }
        if(outputHandlers == null) {
            outputHandlers = new HashMap<>();
        }
        if(settingsHandlers == null) {
            settingsHandlers = new HashMap<>();
        }
        File path = new File(dir);
        for(File file : Objects.requireNonNull(
                path.listFiles(pathname ->
                        (depth > 0 && pathname.isDirectory())
                                || (pathname.isFile()
                                && pathname.getName().toLowerCase().endsWith(".jar"))
                ))
        ){
            if(file.isFile()){
                loadJarFile(file);
            } else if(file.isDirectory()){
                loadFromFolder(file.getCanonicalPath(), depth - 1);
            }
        }
    }

    protected void loadJarFile(File jarFile) throws IOException {
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL()}); JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".class")) {
                    String className = entry.getName().replace("/", ".").replace(".class", "");
                    Class<?> clazz = classLoader.loadClass(className);
                    if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
                        continue;
                    }
                    allModClasses.put(className, clazz);
                    if (Node.class.isAssignableFrom(clazz)) {
                        nodes.put(className, (Class<? extends Node>) clazz);
                    }
                    if (IInputHandler.class.isAssignableFrom(clazz)) {
                        inputHandlers.put(className, (Class<? extends IInputHandler>) clazz);
                    }
                    if (IOutputHandler.class.isAssignableFrom(clazz)) {
                        outputHandlers.put(className, (Class<? extends IOutputHandler>) clazz);
                    }
                    if (ISettingsHandler.class.isAssignableFrom(clazz)) {
                        settingsHandlers.put(className, (Class<? extends ISettingsHandler>) clazz);
                    }
                    if (ISettings.class.isAssignableFrom(clazz)) {
                        settings.put(className, (Class<? extends ISettings>) clazz);
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Mod file \"" + jarFile.getName() + "\" cannot be load properly, contact Mod creator with these information:");
            System.err.println(e.getLocalizedMessage());
            System.err.println("Trying to proceed...");
        }
    }
}

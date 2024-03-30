package org.techaurora.nodenet.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface Factory {
    /**
     * Initialize the Factory
     * @param classSet The Map of class name and corresponding classes
     */
    public void init(Map<String, Class<?>> classSet);

    /**
     * Create instance use class name
     * @param className The name of the class to be build
     * @return A new instance of the class with given name
     *
     */
    public Object build(String className);
}

package org.techaurora.nodenet.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface Factory {
    public void init(Map<String, Class<?>> classSet);
    public Object build(String className);
}

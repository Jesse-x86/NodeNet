package org.techaurora.nodenet.settings;

public interface Settings<T> {
    public boolean setValue(Object value);
    public T getValue();
}

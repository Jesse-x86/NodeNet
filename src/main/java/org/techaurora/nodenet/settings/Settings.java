package org.techaurora.nodenet.settings;

public interface Settings<T> {
    public void init(String name, Class<T> type, T value, Validator validator);
    public boolean setValue(Object value);
    public T getValue();
}

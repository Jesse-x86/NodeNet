package org.techaurora.nodenet.settings;

@FunctionalInterface
public interface Validator {
    boolean validate(Object obj, Class<?> type);
}



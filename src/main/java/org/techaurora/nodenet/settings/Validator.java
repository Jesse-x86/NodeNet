package org.techaurora.nodenet.settings;

@FunctionalInterface
public interface Validator {
    boolean validate(Object obj);
    public static final class NotNull implements Validator{
        @Override
        public boolean validate(Object obj) {
            return obj != null;
        }
    }

    public static final class Nullable implements Validator{
        @Override
        public boolean validate(Object obj) {
           return true;
        }
    }
}



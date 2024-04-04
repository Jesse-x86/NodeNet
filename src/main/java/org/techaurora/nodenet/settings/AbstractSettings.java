package org.techaurora.nodenet.settings;

public abstract class AbstractSettings implements Settings {
    protected String name;
    protected Object value;
    protected Validator validator;
    protected Class<?> type;

    @Override
    public Settings init(String name, Class<?> type, Object value, Validator validator){
        this.name = name;
        this.type = type;
        this.validator = validator;
        setValue(value);
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean setValue(Object value){
        if(validate(value)){
            this.value = type.cast(value);
            return true;
        }
        return false;
    }

    @Override
    public Object getValue(){
        return value;
    }

    @Override
    public boolean validate(Object value) {
        // if type is null or obj is qualified type, proceed
        // otherwise, return false
        if(null == type
                || null == value
                || type.isInstance(value)
        ){
            // if Validator exist, use validator
            // otherwise, return apply default
            if (validator != null) {
                return validator.validate(value);
            }
            return new Validator.NotNull().validate(value);
        }
        return false;
    }
}

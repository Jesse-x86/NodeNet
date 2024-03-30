package org.techaurora.nodenet.settings;

public abstract class AbstractSettings<T> implements Settings{
    protected String name;
    protected T value;
    protected Validator validator;
    protected Class<T> type;

    public void init(String name, Class type, Object value, Validator validator){
        this.name = name;
        this.type = type;
        this.validator = validator;
        setValue(value);
    }

//    public void setValidator(Validator validator) {
//        this.validator = validator;
//    }

    public boolean setValue(Object value){
        if(null == validator ||
                (type.isInstance(value) && validator.validate(value))
        ){
            this.value = type.cast(value);
            return true;
        }
        return false;
    }

    public T getValue(){
        return value;
    }
}

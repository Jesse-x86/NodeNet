package org.techaurora.nodenet.settings;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSettingsHandler implements SettingsHandler{

    List<Settings> settingsList;

    public AbstractSettingsHandler(){

    }

    public void init(List<String> names, List<Class<?>> types, List<Object> values, List<Validator> validators){
        try{
            settingsList = new ArrayList<>();
            int l = names.size();
            for(int i = 0; i < l; i++){
                settingsList.add(new SettingsInstance(names.get(i), types.get(i), values.get(i), validators.get(i)));
            }
        } catch (Exception e){
            //TODO
        }
    }

    private boolean validate(Validator validator, Object obj){
        if(null == validator) return true;
        return(validator.validate(obj));
    }
}

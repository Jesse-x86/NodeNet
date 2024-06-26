package org.techaurora.nodenet.settings;

public interface ISettings {
    /**
     * Initialize the settings
     * @param name Name of the setting, will be displayed on front end
     * @param type Type of the setting, will be used to regulate the value
     * @param value Default value of the setting, can be null
     * @param validator Validator of the setting, can be null
     */
    public ISettings init(String name, Class<?> type, Object value, Validator validator);
    public String getName();

    /**
     * Update the value of the setting
     * @param value new value to be set
     * @return whether the operation succeed
     */
    public boolean setValue(Object value);

    /**
     * Get the value of the setting
     * @return the Value
     */
    public Object getValue();

    /**
     * validate value, calls the validator if it's not null and given value is valid type
     * @return true if validate pass, false if not
     */
    public boolean validate(Object value);
}

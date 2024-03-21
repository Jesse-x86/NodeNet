package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Validator;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractNode implements Node {
    InputHandler inputHandler;

    /**
     *
     * @param inputHandler
     */
    static List<Class<?>> inputTypes, settingsTypes, outputTypes;
    static List<Validator> settingsValidators;

    public void setInputHandler(InputHandler inputHandler) {
        inputHandler.init(this);
        this.inputHandler = inputHandler;
    }

}

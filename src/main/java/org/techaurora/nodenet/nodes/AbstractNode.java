package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.utils.DefaultInputHandler;

public abstract class AbstractNode implements Node {
    DefaultInputHandler inputHandler;

    /**
     *
     * @param inputHandler
     */
    public void setInputHandler(DefaultInputHandler inputHandler){
        this.inputHandler = inputHandler;
    }


}

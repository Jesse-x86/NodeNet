package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;

public abstract class AbstractNode implements Node {
    InputHandler inputHandler;

    /**
     *
     * @param inputHandler
     */

    public void setInputHandler(InputHandler inputHandler) {
        inputHandler.init(this);
        this.inputHandler = inputHandler;
    }

}

package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.utils.InputHandler;

/**
 * The interface of Node, the very basic component of any node network.
 * Typically a Node should contain a InputHandler, and a static OutputHandler
 *
 */
public interface Node {
    public void setInputHandler(InputHandler inputHandler);
    public String getMethodName();


}

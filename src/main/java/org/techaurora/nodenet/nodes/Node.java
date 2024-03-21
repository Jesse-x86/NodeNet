package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.utils.InputHandler;

import java.util.List;

/**
 * The interface of Node, the very basic component of any node network.
 * Typically a Node should contain a InputHandler, and a static OutputHandler
 *
 */
public interface Node {
    public void setInputHandler(InputHandler inputHandler);
    public String getMethodName();

    public Class<?> getInputType(int index);
    public List<Class<?>> getInputTypes();
    public Class<?> getOutputType(int index);
    public List<Class<?>> getOutputTypes();


}

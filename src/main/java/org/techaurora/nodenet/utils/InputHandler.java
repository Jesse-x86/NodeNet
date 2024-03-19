package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

/**
 * InputHandler are used to deal with different type of input handling logics
 * while not having to be embedded in Nodes.
 */
public interface InputHandler {

    /**
     * Initialize the InputHandler
     * @param node Node object the current InputHandler is attached to
     */
    public void init(Node node);

    /**
     *
     * @param input The Object Input
     * @param index
     * @throws IndexOutOfBoundsException
     */
    public void insert(Object input, int index) throws IndexOutOfBoundsException;
    public boolean isAvaliable();

    /**
     *
     * @return True if all input that are not null-able are available
     * False if not all input are available
     */
    public void invoke();
}

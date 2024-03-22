package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.List;

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
//    public void input(int index, Object input) throws IndexOutOfBoundsException;
    public void input(int index, Object input, boolean isPersistent) throws IndexOutOfBoundsException;

    /**
     *
     * @return True if all input that are not null-able are available
     * False if not all input are available
     */
    public boolean isAvaliable();
    public List<Object> provide();
    public List<Object> provideCopy();
    public List<Object> provideAndEmpty();
}

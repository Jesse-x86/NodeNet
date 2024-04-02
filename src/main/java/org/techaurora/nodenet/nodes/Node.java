package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.container.Container;
import org.techaurora.nodenet.settings.Validator;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;
import org.techaurora.nodenet.utils.OutputRouter;

import java.util.List;

/**
 * The interface of Node, the very basic component of any node network.
 * Typically a Node should contain a InputHandler, and a static OutputHandler
 *
 */
public interface Node {

    public Node setContainer(Container container);
    public Container getContainer();
    /**
     * Set the types of all input for current Node
     * @param inputTypes
     */
    public Node setInputTypes(List<Class<?>> inputTypes);
    /**
     * Set the types of all output for current Node
     * @param outputTypes
     */
    public Node setOutputTypes(List<Class<?>> outputTypes);
    /**
     * Set the types of all inputValidators for current Node
     * @param inputValidators
     */
    public Node setInputValidators(List<Validator> inputValidators);

    public Node setInputHandler(InputHandler inputHandler);
    public Node setOutputHandler(OutputHandler outputHandler);

    public Class<?> getInputType(int index) throws IndexOutOfBoundsException;
    public List<Class<?>> getInputTypes();
    public Class<?> getOutputType(int index) throws IndexOutOfBoundsException;


    /**
     * @return A list of the type of outputs, index corresponding to the output index
     */
    public List<Class<?>> getOutputTypes();

    public Validator getInputValidator(int index) throws IndexOutOfBoundsException;

    public List<Validator> getInputValidators();


    public void input(int index, Object obj, boolean isPersistent);

    /**
     * Check whether the input is ready. If input is ready, then proceed.
     */
    public void checkAndProceed();

    /**
     * Connect current node's output to some node's input
     * @param index the output index
     * @param target the target node
     * @param targetIndex the target node's input index
     */
    public void connect(int index, Node target, int targetIndex);
    /**
     * Disconnect current node's output to some node's input
     * @param index the output index
     * @param target the target node
     * @param targetIndex the target node's input index
     */
    public void disconnect(int index, Node target, int targetIndex);
}

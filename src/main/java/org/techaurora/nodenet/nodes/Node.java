package org.techaurora.nodenet.nodes;

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
    public void setInputHandler(InputHandler inputHandler);
    public void setOutputHandler(OutputHandler outputHandler);

    public Class<?> getInputType(int index) throws IndexOutOfBoundsException;
    public List<Class<?>> getInputTypes();
    public Class<?> getOutputType(int index) throws IndexOutOfBoundsException;
    public List<Class<?>> getOutputTypes();
    public Validator getInputValidator(int index) throws IndexOutOfBoundsException;
    public List<Validator> getInputValidators();

    public void input(int index, Object obj, boolean isPersistent);
    public void checkAndProceed();

    public void connect(int index, Node target, int targetIndex);
    public void disconnect(int index, Node target, int targetIndex);
}

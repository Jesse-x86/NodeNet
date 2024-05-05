package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.container.Container;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;
import org.techaurora.nodenet.utils.StringIndexedArray;

import java.util.Map;

public abstract class AbstractNode implements Node {

    protected Container container;
    protected static final Runnable core = null;

    @Override
    public Node setContainer(Container container) {
        this.container = container;
        return this;
    }

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public StringIndexedArray<IOTypeValidateObject> getInputValidateObjs() {
        return inputTypes;
    }

    @Override
    public StringIndexedArray<IOTypeValidateObject> getOutputValidateObjs() {
        return outputTypes;
    }

    /**
     * You should write your own proceed function for each Node you make,
     * this by default will only be invoked by checkAndProceed()
     * thus you can assume you have all input you need in objects
     * @param objects All the input you need
     */
    protected abstract void proceed(Map<String, Object> objects);
}

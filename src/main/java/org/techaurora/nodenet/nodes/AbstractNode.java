package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.container.Container;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;
import org.techaurora.nodenet.utils.StringIndexedArray;

import java.util.Map;

public abstract class AbstractNode implements Node {

    protected Container container;
    protected static final Runnable core = null;
    private final long instanceID;

    public AbstractNode(Container container, long instanceID){
        this.container = container;
        this.instanceID = instanceID;
    }

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public Map<String, IOTypeValidateObject> getInputValidateObjs() {
        return inputTypes;
    }

    @Override
    public Map<String, IOTypeValidateObject> getOutputValidateObjs() {
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

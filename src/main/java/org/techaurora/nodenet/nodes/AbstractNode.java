package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.container.Container;
import org.techaurora.nodenet.settings.Validator;
import org.techaurora.nodenet.utils.IInputHandler;
import org.techaurora.nodenet.utils.IOutputHandler;

import java.util.Map;

public abstract class AbstractNode implements Node {

    protected Container container;
    protected IInputHandler inputHandler;
    protected IOutputHandler outputHandler;

    @Override
    public Node setContainer(Container container) {
        this.container = container;
        return this;
    }

    @Override
    public Container getContainer() {
        return container;
    }

    /**
     * @param inputHandler The InputHandler to inject
     */
    @Override
    public Node setInputHandler(IInputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.inputHandler.init(this);
        return this;
    }

    /**
     * @param outputHandler The OutputHandler to inject
     */
    @Override
    public Node setOutputHandler(IOutputHandler outputHandler) {
        this.outputHandler = outputHandler;
        this.outputHandler.init(this);
        return this;
    }

    @Override
    public Node setIOValidateObj(Map<String, IOTypeValidateObject> map, String ID, Class<?> type, Validator validator){
        map.put(ID, new IOTypeValidateObject(ID, type, validator));
        return this;
    }

    @Override
    public Class<?> getInputType(String inputID){
        return getInputValidateObj(inputID).getType();
    }
    @Override
    public Class<?> getOutputType(String outputID){
        return getOutputValidateObj(outputID).getType();
    }
    @Override
    public IOTypeValidateObject getInputValidateObj(String inputID){
        return inputTypes.get(inputID);
    }
    @Override
    public IOTypeValidateObject getOutputValidateObj(String outputID){
        return outputTypes.get(outputID);
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
     * @param inputID the ID of the object "slot" to be input-ed into
     * @param obj The object to be input-ed. Wrong type of input cause no error but triggers no input and checkAndProceed()
     * @param isPersistent Whether this input should be saved after calling provide() function,true = save, false = not save, false by default
     */
    @Override
    public void input(String inputID, Object obj, boolean isPersistent) {
        inputHandler.input(inputID, obj, isPersistent);
    }

    /**
     * if all necessary input are ready, then call proceed function
     */
    @Override
    public void checkAndProceed() {
        if(inputHandler.isAvailable()){
            proceed(inputHandler.provide());
        }
    }

    /**
     * You should write your own proceed function for each Node you make,
     * this by default will only be invoked by checkAndProceed()
     * thus you can assume you have all input you need in objects
     * @param objects All the input you need
     */
    protected abstract void proceed(Map<String, Object> objects);


    public boolean connect(String outputID, Node target, String inputID){
        if(outputHandler == null) return false;
        return outputHandler.connect(outputID, target, inputID);
    }
    public boolean disconnect(String outputID, Node target, String inputID){
        if(outputHandler == null) return false;
        return outputHandler.disconnect(outputID, target, inputID);
    }
}

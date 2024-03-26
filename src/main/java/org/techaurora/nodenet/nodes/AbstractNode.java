package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Validator;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;
import org.techaurora.nodenet.utils.OutputRouter;

import java.util.List;

public abstract class AbstractNode implements Node {

    protected InputHandler inputHandler;
    protected OutputHandler outputHandler;
    protected List<Class<?>> inputTypes;
    protected List<Class<?>> outputTypes;
    protected List<Validator> inputValidators;

    public void setInputTypes(List<Class<?>> inputTypes) {
        this.inputTypes = inputTypes;
    }
    public void setOutputTypes(List<Class<?>> outputTypes) {
        this.outputTypes = outputTypes;
    }
    public void setInputValidators(List<Validator> inputValidators) {
        this.inputValidators = inputValidators;
    }

    /**
     * @param inputHandler The InputHandler to inject
     */
    @Override
    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.inputHandler.init(this);
    }

    /**
     * @param outputHandler The OutputHandler to inject
     */
    @Override
    public void setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
        this.outputHandler.init(this);
    }

    /**
     * @param index the index (0-indexed) of input types
     * @return input type at given index
     */
    @Override
    public Class<?> getInputType(int index) throws IndexOutOfBoundsException{
        return inputTypes.get(index);
    }

    /**
     * @return all input types
     */
    @Override
    public List<Class<?>> getInputTypes() {
        return inputTypes;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public Class<?> getOutputType(int index) throws IndexOutOfBoundsException{
        return outputTypes.get(index);
    }

    /**
     * @return
     */
    @Override
    public List<Class<?>> getOutputTypes() {
        return outputTypes;
    }
    public Validator getInputValidator(int index) throws IndexOutOfBoundsException{
        return inputValidators.get(index);
    }
    public List<Validator> getInputValidators(){
        return inputValidators;
    }

    /**
     * @param obj
     * @param index
     */
    @Override
    public void input(int index, Object obj, boolean isPersistent) {
        inputHandler.input(index, obj, isPersistent);
        checkAndProceed();
    }

    /**
     *
     */
    @Override
    public void checkAndProceed() {
        if(inputHandler.isAvaliable()){
            proceed(inputHandler.provide());
        }
    }

    protected abstract void proceed(List<Object> objects);


    public void connect(int index, Node target, int targetIndex){
        outputHandler.connect(index, new OutputRouter(targetIndex, target));
    }
    public void disconnect(int index, Node target, int targetIndex){
        outputHandler.disconnect(index, new OutputRouter(targetIndex, target));
    }
}

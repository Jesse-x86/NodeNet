package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.container.Container;
import org.techaurora.nodenet.settings.Validator;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;
import org.techaurora.nodenet.utils.OutputRouter;

import java.util.List;

public abstract class AbstractNode implements Node {

    protected Container container;
    protected InputHandler inputHandler;
    protected OutputHandler outputHandler;
    protected List<Class<?>> inputTypes;
    protected List<Class<?>> outputTypes;
    protected List<Validator> inputValidators;

    @Override
    public Node setContainer(Container container) {
        this.container = container;
        return this;
    }

    @Override
    public Container getContainer() {
        return container;
    }

    public Node setInputTypes(List<Class<?>> inputTypes) {
        this.inputTypes = inputTypes;
        return this;
    }
    public Node setOutputTypes(List<Class<?>> outputTypes) {
        this.outputTypes = outputTypes;
        return this;
    }
    public Node setInputValidators(List<Validator> inputValidators) {
        this.inputValidators = inputValidators;
        return this;
    }

    /**
     * @param inputHandler The InputHandler to inject
     */
    @Override
    public Node setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.inputHandler.init(this);
        return this;
    }

    /**
     * @param outputHandler The OutputHandler to inject
     */
    @Override
    public Node setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
        this.outputHandler.init(this);
        return this;
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
     * {@inheritDoc}
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
     * @param index the index of the objected to be inputed into
     * @param obj The object to be input-ed. Wrong type of input cause no error but triggers no input and checkAndProceed()
     * @param isPersistent Whether this input should be saved after calling provide() function,true = save, false = not save, false by default
     */
    @Override
    public void input(int index, Object obj, boolean isPersistent) {
        try {
            inputHandler.input(index, obj, isPersistent);
            checkAndProceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

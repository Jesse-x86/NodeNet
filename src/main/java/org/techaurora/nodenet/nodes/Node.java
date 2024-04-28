package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.container.Container;
import org.techaurora.nodenet.settings.Validator;
import org.techaurora.nodenet.utils.IInputHandler;
import org.techaurora.nodenet.utils.IOutputHandler;

import java.util.Map;

/**
 * The interface of Node, the very basic component of any node network.
 * Typically a Node should contain a InputHandler, and a static OutputHandler
 *
 */
public interface Node {



    public Node setContainer(Container container);
    public Container getContainer();
    public Node setInputHandler(IInputHandler inputHandler);
    public Node setOutputHandler(IOutputHandler outputHandler);

    public Node setIOValidateObj(Map<String, IOTypeValidateObject> map, String ID, Class<?> type, Validator validator);

    public Class<?> getInputType(String inputID);
    public Class<?> getOutputType(String inputID);
    public IOTypeValidateObject getInputValidateObj(String inputID);
    public IOTypeValidateObject getOutputValidateObj(String outputID);
    public Map<String, IOTypeValidateObject> getInputValidateObjs();
    public Map<String, IOTypeValidateObject> getOutputValidateObjs();


    public void input(String inputID, Object obj, boolean isPersistent);

    /**
     * Check whether the input is ready. If input is ready, then proceed.
     */
    public void checkAndProceed();

    /**
     * Connect current node's output to some node's input
     * @param outputID the output ID
     * @param target the target node
     * @param inputID the target node's input ID
     */
    public boolean connect(String outputID, Node target, String inputID);
    /**
     * Disconnect current node's output to some node's input
     * @param outputID the output ID
     * @param target the target node
     * @param inputID the target node's input ID
     */
    public boolean disconnect(String outputID, Node target, String inputID);

    public class IOTypeValidateObject {
        public String ID;
        public Class<?> type;
        public Validator validator;
        public IOTypeValidateObject(String ID, Class<?> type, Validator validator){
            this.ID = ID;
            this.type = type;
            this.validator = validator;
        }

        public boolean validate(Object obj){
            // if type is null or obj is qualified type, proceed
            // otherwise, return false
            if(null == type
                    || null == obj
                    || type.isInstance(obj)
            ){
                // if Validator exist, use validator
                // otherwise, return apply default
                if (validator != null) {
                    return validator.validate(obj);
                }
                return new Validator.NotNull().validate(obj);
            }
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            if(IOTypeValidateObject.class.isInstance(obj)){
                return ID.equals(((IOTypeValidateObject) obj).ID)
                        && type.equals(((IOTypeValidateObject) obj).type)
                        && validator.equals(((IOTypeValidateObject) obj).validator);
            }
            return super.equals(obj);
        }
    }
}

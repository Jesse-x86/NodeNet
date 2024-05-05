package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.container.Container;
import org.techaurora.nodenet.settings.Validator;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;
import org.techaurora.nodenet.utils.StringIndexedArray;

import java.util.*;

/**
 * The interface of Node, the very basic component of any node network.
 * Typically a Node should contain a InputHandler, and a static OutputHandler
 *
 */
public interface Node {
    public final static StringIndexedArray<IOTypeValidateObject> inputTypes = null;
    public final static StringIndexedArray<IOTypeValidateObject> outputTypes = null;

    public Node setContainer(Container container);
    public Container getContainer();

    public StringIndexedArray<IOTypeValidateObject> getInputValidateObjs();
    public StringIndexedArray<IOTypeValidateObject> getOutputValidateObjs();

    class IOTypeValidateObject {
        private String ID;

        public Class<?> getType() {
            return type;
        }

        private final Class<?> type;
        private Validator validator;
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

    /**
     * I/O Types Validation map builder
     * so that nodes don't have to write a separate static Constructor just to
     * initialize input/outputTypes obj.
     */
    final class IOTypeValidateObjectBuilder{
        List<String> ids;
        List<IOTypeValidateObject> data;
        IOTypeValidateObjectBuilder(){
            ids = new ArrayList<>();
            data = new ArrayList<>();
        }
        public IOTypeValidateObjectBuilder add(String ID, Class<?> type, Validator validator){
            ids.add(ID);
            data.add(new IOTypeValidateObject(ID, type, validator));
            return this;
        }

        public StringIndexedArray<IOTypeValidateObject> build(){
            return new StringIndexedArray<>(ids, data).unmodifiableCopy();
        }
    }
}

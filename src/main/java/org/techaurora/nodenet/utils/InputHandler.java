package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.Map;

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
     * @param inputID
     * @param inputObj
     * @param isPersistent
     */
    public void input(String inputID, Object inputObj, boolean isPersistent);

    /**
     *
     * @return True if all input that are not null-able are available
     * False if not all input are available
     */
    public boolean isAvailable();
    public Map<String, Object> provide();
    public Map<String, Object> provideAndEmpty();

    class InputStorageObject {
        public Object object;
        public Boolean isPersistent;

        public InputStorageObject(){};
        public InputStorageObject(Object object, Boolean isPersistent){
            this.object = object;
            this.isPersistent = isPersistent;
        }

        @Override
        public boolean equals(Object obj) {
            if(InputStorageObject.class.isInstance(obj)){
                return object.equals(((InputStorageObject) obj).object) && isPersistent.equals(((InputStorageObject) obj).isPersistent);
            }
            return super.equals(obj);
        }
    }
}

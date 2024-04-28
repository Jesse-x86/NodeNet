package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.List;
import java.util.Map;

public interface IOutputHandler {
    /**
     * Initialize module
     * @param node the node obj this handler is attached to
     */
    public void init(Node node);

    /**
     * Initialize module from existing instance, mainly for replacing outputHandlers
     * @param outputHandler existing outputHandler
     */
    public void init(IOutputHandler outputHandler);

    /**
     * Connect to Node, will send data to all existing connection
     * when calling output()
     * @param outputID the current outputHandler outputID
     * @param target the target Node to connect to
     * @param inputID the input ID of given node
     * @return true if connection success, false if not
     */
    public boolean connect(String outputID, Node target, String inputID);

    /**
     *
     * @param outputID
     * @param target
     * @param inputID
     * @return
     */
    public boolean disconnect(String outputID, Node target, String inputID);
    public void output(String outputID, Object obj, boolean isPersistent);
    public void output(String outputID, Object obj, boolean isPersistent, boolean isSilentMode);

    /**
     * get the Node current OutputHandler is attached to
     * @return
     */
    public Node getNode();

    /**
     * get the OutputRouters map current OutputHandler is holding, mainly for replacing init() function
     * @return
     */
    public Map<String, List<OutputRouterObject>> getOutputRouters();

    class OutputRouterObject {
        public String inputID;
        public Node node;

        public OutputRouterObject(){};
        public OutputRouterObject(String inputID, Node node){
            this.inputID = inputID;
            this.node = node;
        }

        @Override
        public boolean equals(Object obj) {
            if(OutputRouterObject.class.isInstance(obj)){
                return inputID.equals(((OutputRouterObject) obj).inputID) && node.equals(((OutputRouterObject) obj).node);
            }
            return super.equals(obj);
        }
    }
}

package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.io.Serializable;

public class NodeConnection implements Serializable {
    /**
     * key of input node
     */
    private Node inputNode;
    /**
     * key of output node
     */
    private Node outputNode;
    /**
     * string ID of input
     */
    private int inputID;
    /**
     * string ID of output
     */
    private int outputID;

    private NodeConnection(Node output, int outputID, Node input, int inputID){
        this
                .setInputID(inputID)
                .setOutputID(outputID)
                .setInputNode(input)
                .setOutputNode(output);
    }

    public Node getInputNode() {
        return inputNode;
    }

    public Node getOutputNode() {
        return outputNode;
    }

    public int getInputID() {
        return inputID;
    }

    public int getOutputID() {
        return outputID;
    }

    private NodeConnection setInputNode(Node inputNode) {
        this.inputNode = inputNode;
        return this;
    }

    private NodeConnection setOutputNode(Node outputNode) {
        this.outputNode = outputNode;
        return this;
    }

    private NodeConnection setInputID(int inputID) {
        this.inputID = inputID;
        return this;
    }

    private NodeConnection setOutputID(int outputID) {
        this.outputID = outputID;
        return this;
    }

    public static NodeConnection build(Node output, int outputID, Node input, int inputID){
        NodeConnection nc = new NodeConnection(output, outputID, input, inputID);
        try {
            if (!nc.inputNode.getInputValidateObjs().get(inputID).getType().isAssignableFrom(nc.outputNode.getOutputValidateObjs().get(outputID).getType())) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e){
            throw new IllegalArgumentException(e);
        }
        return nc;
    }
}

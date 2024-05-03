package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.WrapperNode;

public class NodeConnection {
    /**
     * key of input node
     */
    WrapperNode inputNode;
    /**
     * key of output node
     */
    WrapperNode outputNode;
    /**
     * string ID of input
     */
    String inputID;
    /**
     * string ID of output
     */
    String outputID;

    public NodeConnection(WrapperNode output, String outputID, WrapperNode input, String inputID){
        this
                .setInputID(inputID)
                .setOutputID(outputID)
                .setInputNode(input)
                .setOutputNode(output);
    }

    public WrapperNode getInputNode() {
        return inputNode;
    }

    public NodeConnection setInputNode(WrapperNode inputNode) {
//        if(null == inputNode) throw new IllegalArgumentException();
        this.inputNode = inputNode;
        return this;
    }

    public WrapperNode getOutputNode() {
        return outputNode;
    }

    public NodeConnection setOutputNode(WrapperNode outputNode) {
        this.outputNode = outputNode;
        return this;
    }

    public String getInputID() {
        return inputID;
    }

    public NodeConnection setInputID(String inputID) {
        this.inputID = inputID;
        return this;
    }

    public String getOutputID() {
        return outputID;
    }

    public NodeConnection setOutputID(String outputID) {
        this.outputID = outputID;
        return this;
    }
}

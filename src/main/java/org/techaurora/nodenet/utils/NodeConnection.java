package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.container.Container;

public class NodeConnection {
    /**
     * key of input node
     */
    Container.FakeNode inputNode;
    /**
     * key of output node
     */
    Container.FakeNode outputNode;
    /**
     * string ID of input
     */
    String inputID;
    /**
     * string ID of output
     */
    String outputID;

    public NodeConnection(Container.FakeNode output, String outputID, Container.FakeNode input, String inputID){
        this
                .setInputID(inputID)
                .setOutputID(outputID)
                .setInputNode(input)
                .setOutputNode(output);
    }

    public Container.FakeNode getInputNode() {
        return inputNode;
    }

    public NodeConnection setInputNode(Container.FakeNode inputNode) {
//        if(null == inputNode) throw new IllegalArgumentException();
        this.inputNode = inputNode;
        return this;
    }

    public Container.FakeNode getOutputNode() {
        return outputNode;
    }

    public NodeConnection setOutputNode(Container.FakeNode outputNode) {
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

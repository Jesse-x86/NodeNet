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

    public Container.FakeNode getInputNode() {
        return inputNode;
    }

    public void setInputNode(Container.FakeNode inputNode) {
        this.inputNode = inputNode;
    }

    public Container.FakeNode getOutputNode() {
        return outputNode;
    }

    public void setOutputNode(Container.FakeNode outputNode) {
        this.outputNode = outputNode;
    }

    public String getInputID() {
        return inputID;
    }

    public void setInputID(String inputID) {
        this.inputID = inputID;
    }

    public String getOutputID() {
        return outputID;
    }

    public void setOutputID(String outputID) {
        this.outputID = outputID;
    }
}

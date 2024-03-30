package org.techaurora.nodenet.container;

import org.techaurora.nodenet.nodes.Node;

import java.util.Set;

public abstract class AbstractContainer implements Container {
    Set<Node> nodes;

    @Override
    public void connect(Node output, int outputIndex, Node input, int inputIndex) {
        output.connect(outputIndex, input, inputIndex);
    }

    @Override
    public void disconnect(Node output, int outputIndex, Node input, int inputIndex) {
        output.disconnect(outputIndex, input, inputIndex);
    }

    @Override
    public void start() {
        for (Node n: nodes) {
            n.checkAndProceed();
        }
    }
}

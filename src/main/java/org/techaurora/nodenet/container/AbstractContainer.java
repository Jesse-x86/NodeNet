package org.techaurora.nodenet.container;

import org.techaurora.nodenet.nodes.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractContainer implements Container {
    Set<Node> nodes;
    Map<String, Object> globalVars;

    public AbstractContainer(){
        nodes = new HashSet<>();
        globalVars = new HashMap<>();
    }

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

    @Override
    public void setGlobalVar(String name, Object obj) {
        globalVars.put(name, obj);
    }

    @Override
    public Object getGlobalVar(String name) {
        return globalVars.get(name);
    }
}

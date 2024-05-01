package org.techaurora.nodenet.container;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.utils.NodeConnection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractContainer implements Container {
    Set<Node> nodes;
    Map<String, Object> globalVars;
    Map<Long, FakeNode> nodeMap;


    public AbstractContainer(){
        nodes = new HashSet<>();
        globalVars = new HashMap<>();
    }

    public void connect(FakeNode output, String outputID, FakeNode input, String inputID){
        NodeConnection nc = new NodeConnection(output, outputID, input, inputID);
        output.connectOutward(nc);
        input.connectInward(nc);
    }

    @Override
    public void connect(Node output, String outputID, Node input, String inputID) {
        output.connect(outputID, input, inputID);
    }

    @Override
    public void disconnect(Node output, String outputID, Node input, String inputID) {
        output.disconnect(outputID, input, inputID);
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

package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.NodeConnection;
import org.techaurora.nodenet.utils.NodeExecutor;
import org.techaurora.nodenet.utils.OutputHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// Global Variable implementation?
public class WrapperNode implements Serializable {
    long key;
    CoreNode coreNode;

    InputHandler inputHandler;
    OutputHandler outputHandler;
    NodeExecutor nodeExecutor;
    HashMap<Long, List<NodeConnection>> inwardConnections;
    HashMap<Long, List<NodeConnection>> outwardConnections;

    public WrapperNode(long key, CoreNode coreNode) {
        this.key = key;
        this.coreNode = coreNode;
        this.inwardConnections = new HashMap<>();
        this.outwardConnections = new HashMap<>();
    }



    protected void connectInward(NodeConnection connection) {
        List<NodeConnection> lst = inwardConnections
                .getOrDefault(connection.getOutputNode().key, new ArrayList<>());
        lst.add(connection);
        inwardConnections.put(connection.getOutputNode().key, lst);
    }

    protected void connectOutward(NodeConnection connection) {
        List<NodeConnection> lst = outwardConnections
                .getOrDefault(connection.getInputNode().key, new ArrayList<>());
        lst.add(connection);
        outwardConnections.put(connection.getInputNode().key, lst);
    }

}

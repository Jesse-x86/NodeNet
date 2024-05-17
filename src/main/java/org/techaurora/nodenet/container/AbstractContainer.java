package org.techaurora.nodenet.container;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.utils.NodeConnection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractContainer implements Container {
    Map<String, Object> globalVars;
    Map<Long, Node> nodeMap;

    AtomicLong instanceIDManager = new AtomicLong();

    transient ExecutorService pool = Executors.newCachedThreadPool();


    public AbstractContainer(){
        globalVars = new HashMap<>();
    }

    public void connect(Node output, int outputID, Node input, int inputID){
        NodeConnection nc = NodeConnection.build(output, outputID, input, inputID);
    }

    @Override
    public void connect(Node output, String outputID, Node input, String inputID) {

    }

    @Override
    public void disconnect(Node output, String outputID, Node input, String inputID) {

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

package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionHandler implements Serializable {
    private Node node;
    /**
     * A map of connections, categorized by Nodes its connected to
     * String: Node ID
     */
    private Map<String, List<NodeConnection>> inwardConnections;
    private Map<String, List<NodeConnection>> outwardConnections;

    ConnectionHandler(Node node){
        inwardConnections = new HashMap<>();
        outwardConnections = new HashMap<>();
        this.node = node;
    }


    public void connectInward(NodeConnection connection) {

    }

    public void connectOutward(NodeConnection connection) {

    }

    public void disconnectInward(NodeConnection connection){

    }

    public void disconnectOutward(NodeConnection connection){

    }

    public void deleteALLInward(Node node){

    }
}

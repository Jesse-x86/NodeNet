package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class ConnectionHandler {
    private Node node;
    private StringIndexedArray<NodeConnection> inwardConnections;
    private StringIndexedArray<NodeConnection> outwardConnections;

    ConnectionHandler(Node node){
        inwardConnections = new StringIndexedArray<>();
        outwardConnections = new StringIndexedArray<>();
        this.node = node;
    }


    public void connectInward(NodeConnection connection) {
        connect(inwardConnections, connection);
    }

    public void connectOutward(NodeConnection connection) {
        connect(outwardConnections, connection);
    }

    public void disconnectInward(NodeConnection connection){
        disconnect(inwardConnections, connection);
    }

    public void disconnectOutward(NodeConnection connection){
        disconnect(outwardConnections, connection);
    }

    private static void connect(StringIndexedArray<NodeConnection> connections, NodeConnection nodeConnection){
        synchronized (connections) {
            connections.put(nodeConnection);
        }
    }

    private static void disconnect(StringIndexedArray<NodeConnection> connections, NodeConnection nodeConnection){
        synchronized (connections) {
            for (int i = 0; i < connections.size(); i++) {
                if (connections.get(i).equals(nodeConnection)){
                    connections.set(i, connections.get(connections.size() - 1));
                    connections.remove(connections.size() - 1);
                }
            }
        }
    }

}

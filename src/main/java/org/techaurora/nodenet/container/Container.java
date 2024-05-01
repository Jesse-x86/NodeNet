package org.techaurora.nodenet.container;

import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.utils.NodeConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Container is used for containing a Node network. Each container should hold and maintain one Node network. &nbsp;
 * Container should have the ability to build Nodes, initializing them and basic managements such as connecting
 */
public interface Container extends Serializable {
    /**
     * Connect an output to an input
     * @param output The output Node, the Node throws data as output
     * @param outputID The ID of the output
     * @param input The input Node, the Node that takes data as input
     * @param inputID The ID of the input
     */
    public void connect(Node output, String outputID, Node input, String inputID);

    /**
     * Disconnect an output from an input
     * @param output The output Node, the Node throws data as output
     * @param outputID The ID of the output
     * @param input The input Node, the Node that takes data as input
     * @param inputID The ID of the input
     */
    public void disconnect(Node output, String outputID, Node input, String inputID);

    /**
     * Start all nodes within the container (would still skip those who requires data to launch)
     */
    public void start();

    /**
     * Save function, not implemented yet, may change format in future
     */
    public void save();

    /**
     * Load function, not implemented yet, may change format in future
     */
    public void load();

    public Object getGlobalVar(String name);
    public void setGlobalVar(String name, Object obj);

    // Global Variable implementation?
    class FakeNode implements Serializable {
        long key;
        Node node;
        HashMap<Long, List<NodeConnection>> inwardConnections;
        HashMap<Long, List<NodeConnection>> outwardConnections;
        public FakeNode(long key, Node node){
            this.key = key;
            this.node = node;
            this.inwardConnections = new HashMap<>();
            this.outwardConnections = new HashMap<>();
        }

        protected void connectInward(NodeConnection connection){
            List<NodeConnection> lst = inwardConnections
                    .getOrDefault(connection.getOutputNode().key, new ArrayList<>());
            lst.add(connection);
            inwardConnections.put(connection.getOutputNode().key, lst);
        }

        protected void connectOutward(NodeConnection connection){
            List<NodeConnection> lst = outwardConnections
                    .getOrDefault(connection.getInputNode().key, new ArrayList<>());
            lst.add(connection);
            outwardConnections.put(connection.getInputNode().key, lst);
        }

    }
}

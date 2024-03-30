package org.techaurora.nodenet.container;

import org.techaurora.nodenet.nodes.Node;

/**
 * Container is used for containing a Node network. Each container should hold and maintain one Node network. &nbsp;
 * Container should have the ability to build Nodes, initializing them and basic managements such as connecting
 */
public interface Container {
    /**
     * Connect an output to an input
     * @param output The output Node, the Node throws data as output
     * @param outputIndex The index of the output
     * @param input The input Node, the Node that takes data as input
     * @param inputIndex The index of the input
     */
    public void connect(Node output, int outputIndex, Node input, int inputIndex);
    public void disconnect(Node output, int outputIndex, Node input, int inputIndex);

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

    // Global Variable implementation?

}

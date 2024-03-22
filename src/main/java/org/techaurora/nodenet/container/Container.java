package org.techaurora.nodenet.container;

import org.techaurora.nodenet.nodes.Node;

/**
 * Container is used for containing a Node network, initializing them
 * and basic managements such as connecting
 */
public interface Container {
    public void connect(Node output, int outputIndex, Node input, int inputIndex);
    public void disconnect(Node output, int outputIndex, Node input, int inputIndex);

}

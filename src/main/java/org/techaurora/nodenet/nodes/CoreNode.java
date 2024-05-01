package org.techaurora.nodenet.nodes;

import java.util.Map;

/**
 * Node with core logic
 * does not care about input/output
 * run in seperate thread
 */
public abstract class CoreNode implements Runnable{
    private Map<String, Object> args;
    private WrapperNode wrapperNode;

    public CoreNode(Map<String, Object> args, WrapperNode wrapperNode){
        this.args = args;
        this.wrapperNode = wrapperNode;
    }


}

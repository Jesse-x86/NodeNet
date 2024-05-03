package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.CoreNode;
import org.techaurora.nodenet.nodes.WrapperNode;

import java.util.Map;

public abstract class NodeExecutor {
    WrapperNode wrapperNode;
    Class<? extends CoreNode> clazz;
    CoreNode coreNode;

    NodeExecutor(WrapperNode wrapperNode, CoreNode coreNode){
        this.wrapperNode = wrapperNode;
        this.clazz = coreNode.
    }

    public abstract void execute(Map<String, Object> args)

    public static class TimedNode

}

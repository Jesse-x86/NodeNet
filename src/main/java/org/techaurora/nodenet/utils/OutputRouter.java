package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

public class OutputRouter {
    public int index;
    public Node node;

    public OutputRouter(){};
    public OutputRouter(int index, Node node){
        this.index = index;
        this.node = node;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(OutputRouter.class.isInstance(obj)){
//            return index == ((OutputRouter) obj).index && node.equals(((OutputRouter) obj).node);
//        }
//        return super.equals(obj);
//    }
}

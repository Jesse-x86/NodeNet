package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.utils.DefaultOutputHandler;
import org.techaurora.nodenet.utils.OutputHandler;

public class BasicNode extends AbstractNode{

    @Override
    public String getMethodName() {
        return "proceed";
    }

    public OutputHandler proceed(){
        return new DefaultOutputHandler();
    }
}

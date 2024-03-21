package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class DefaultOutputHandler extends AbstractOutputHandler{
    protected Node node;
    protected List<List<OutputRouter>> targets;
    protected int size;

    public DefaultOutputHandler(){

    }

    @Override
    public void init(Node node) {
        this.node = node;
        this.size = node.getOutputTypes().size();
        targets = new ArrayList<>();
        for(int i = 0; i < size; i++){
            targets.add(new ArrayList<>());
        }
    }

    @Override
    public Class<?> getType(int index) {
        return node.getOutputType(index);
    }

    @Override
    public void connect(int index, OutputRouter target) {
        this.targets.get(index).add(target);
    }

    @Override
    public void connect(int index, List<OutputRouter> targets) {
        for(int i = 0; i < targets.size(); i++){
            connect(index, targets.get(i));
        }
    }

    @Override
    public void connect(List<List<OutputRouter>> targets) {
        for(int i = 0; i < size; i++){
            connect(i, targets.get(i));
        }
    }

    @Override
    public void output() {

    }
}

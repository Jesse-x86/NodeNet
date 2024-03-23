package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOutputHandler implements OutputHandler{
    protected Node node;
    protected List<List<OutputRouter>> outputRouters;

    @Override
    public void init(Node node){
        this.node = node;
        if(outputRouters == null || outputRouters.size() != node.getOutputTypes().size()) {
            outputRouters = new ArrayList<>();
            for (int i = 0; i < node.getOutputTypes().size(); i++) {
                outputRouters.add(new ArrayList<>());
            }
        }
    }
    @Override
    public Class<?> getType(int index){
        return node.getOutputType(index);
    }

    @Override
    public void connect(int index, OutputRouter target){
        List<OutputRouter> _cache = outputRouters.get(index);
        if(!_cache.contains(target)){
            _cache.add(target);
        }
    }

    @Override
    public void disconnect(int index, OutputRouter target){
        List<OutputRouter> _cache = outputRouters.get(index);
        _cache.remove(target);
    }

    @Override
    public void output(int index, Object obj, boolean isPersistent){
        List<OutputRouter> _cache = outputRouters.get(index);
        for(OutputRouter outputRouter : _cache){
            outputRouter.node.input(outputRouter.index, obj, isPersistent);
        }
    }

    @Override
    public void setOutputRouters(List<List<OutputRouter>> outputRouters) {
        this.outputRouters = outputRouters;
    }

    @Override
    public List<List<OutputRouter>> getOutputRouters() {
        return outputRouters;
    }
}

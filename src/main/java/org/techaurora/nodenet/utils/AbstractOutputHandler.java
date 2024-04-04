package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.*;

public abstract class AbstractOutputHandler implements OutputHandler{
    protected Node node;
    protected Map<String, List<OutputRouterObject>> outputRouters;

    @Override
    public void init(Node node){
        this.node = node;
        if(outputRouters == null) {
            outputRouters = new HashMap<>();
        }
    }

    @Override
    public void init(OutputHandler outputHandler) {
        this.outputRouters = outputHandler.getOutputRouters();
        init(outputHandler.getNode());
    }

    @Override
    public boolean connect(String outputID, Node node, String inputID){
        if(!node.getInputType(inputID).isAssignableFrom(this.node.getOutputType(outputID))){
            return false;
        }
        OutputRouterObject target = new OutputRouterObject(inputID, node);
        List<OutputRouterObject> _cache = outputRouters.get(outputID);
        if(null == _cache){
            outputRouters.put(outputID, Arrays.asList(target));
        } else if(!_cache.contains(target)){
            _cache.add(target);
        }
        return true;
    }

    @Override
    public boolean disconnect(String outputID, Node node, String inputID){
        List<OutputRouterObject> _cache = outputRouters.get(outputID);
        if(_cache != null) {
            OutputRouterObject target = new OutputRouterObject(inputID, node);
            return _cache.remove(target);
        }
        return false;
    }

    @Override
    public void output(String outputID, Object obj, boolean isPersistent){
        output(outputID, obj, isPersistent, false);
    }

    @Override
    public void output(String outputID, Object obj, boolean isPersistent, boolean isSilentMode) {
        // Only throw output if pass validation
        if(node.getOutputValidateObj(outputID).validate(obj)) {
            // get a list of all output routers
            List<OutputRouterObject> _cache = outputRouters.get(outputID);
            // if list exist
            if (null != _cache) {
                // output for each in list
                for (OutputRouterObject outputRouterObject : _cache) {
                    outputRouterObject.node.input(outputRouterObject.inputID, obj, isPersistent);
                    // if not explicitly called in silent mode, call checkAndProceed()
                    if(!isSilentMode){
                        outputRouterObject.node.checkAndProceed();
                    }
                }
            }
        }
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public Map<String, List<OutputRouterObject>> getOutputRouters() {
        return outputRouters;
    }
}

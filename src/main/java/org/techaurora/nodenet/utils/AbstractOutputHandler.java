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
        // only proceed if output can be cast to input type
        if(!node.getInputType(inputID).isAssignableFrom(this.node.getOutputType(outputID))){
            // TODO: throw an Exception for frontEnd to display
            return false;
        }
        // build RouterObject
        OutputRouterObject target = new OutputRouterObject(inputID, node);
        // find list and insert RouterObject
        List<OutputRouterObject> _cache = outputRouters.get(outputID);
        if(null == _cache){
            _cache = new ArrayList<>();
            _cache.add(target);
            outputRouters.put(outputID, _cache);
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
        if(!node.getOutputValidateObj(outputID).validate(obj)) {
            return;
        }
        // get a list of all output routers
        List<OutputRouterObject> _cache = outputRouters.get(outputID);
        // if list exist
        if (null != _cache) {
            // output for each in list
            for (OutputRouterObject outputRouterObject : _cache) {
                outputRouterObject.node.input(outputRouterObject.inputID, obj, isPersistent);
                // if not explicitly called in silent mode, call checkAndProceed()
                if (!isSilentMode) {
                    outputRouterObject.node.checkAndProceed();
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

package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.util.List;

public interface OutputHandler {
    public void init(Node node);
    public Class<?> getType(int index);
    public void connect(int index, OutputRouter target);
    public void connect(int index, List<OutputRouter> targets);
    public void connect(List<List<OutputRouter>> targets);

    public void output();
}

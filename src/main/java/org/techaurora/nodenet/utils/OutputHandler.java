package org.techaurora.nodenet.utils;

import org.techaurora.nodenet.nodes.Node;

import java.io.Serializable;
import java.util.ArrayList;

public interface OutputHandler extends Serializable {
    public void output(Node outputNode, ArrayList data);
}

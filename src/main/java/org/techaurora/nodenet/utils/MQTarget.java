package org.techaurora.nodenet.utils;

import java.io.Serializable;
import java.util.Map;

public interface MQTarget extends Serializable {
    public void out(Map<String, Object> output, long outputNodeID);
}

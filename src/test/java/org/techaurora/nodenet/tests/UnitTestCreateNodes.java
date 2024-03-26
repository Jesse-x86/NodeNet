package org.techaurora.nodenet.tests;

import org.junit.jupiter.api.Test;
import org.techaurora.nodenet.nodes.Node;
import org.techaurora.nodenet.nodes.TestNodeBasic;
import org.techaurora.nodenet.utils.OutputRouter;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTestCreateNodes {

    @Test
    void OutputRouterTest(){
        Node node = new TestNodeBasic();
        OutputRouter or1 = new OutputRouter(1, node);
        OutputRouter or2 = new OutputRouter(1, node);
        assertEquals(or1, or2);
    }

}

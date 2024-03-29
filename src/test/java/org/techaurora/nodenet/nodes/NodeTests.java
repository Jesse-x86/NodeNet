package org.techaurora.nodenet.nodes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.techaurora.nodenet.factory.TestFactories;
import org.techaurora.nodenet.utils.InputHandler;
import org.techaurora.nodenet.utils.OutputHandler;

public class NodeTests {
    @Test
    public void test1(){
        TestFactories.init();
        Node node1 = (Node) TestFactories.NodeFactory.build("Node");
        Node node2 = (Node) TestFactories.NodeFactory.build("Node");
        Assertions.assertNotNull(node1);
        Assertions.assertNotNull(node2);
    }


    @Test
    public void test2(){
        TestFactories.init();
        Node node1 = (Node) TestFactories.NodeFactory.build("Node");
        Node node2 = (Node) TestFactories.NodeFactory.build("Node");
        node1.setInputHandler((InputHandler) TestFactories.InputHandlerFactory.build("InputHandler"));
        node1.setOutputHandler((OutputHandler) TestFactories.OutputHandlerFactory.build("OutputHandler"));
        node2.setInputHandler((InputHandler) TestFactories.InputHandlerFactory.build("InputHandler"));
        node2.setOutputHandler((OutputHandler) TestFactories.OutputHandlerFactory.build("OutputHandler"));

        System.out.println("Test 1: connect twice (Nothing happens)");
        node1.connect(1, node2, 1);
        node1.connect(1, node2, 1);

        System.out.println("Test 2: null validators(Nothing happens)");
        node1.input(1, "Hello", false);
        System.out.println("Test 3: Trigger proceed and clear in-persistent input(proceed 1)");
        node1.input(0, Integer.valueOf(1111), true);

        System.out.println("Test 4: add persistent input after in-persistent input(proceed 2)");
        node2.input(0, Integer.valueOf(2222), true);

        System.out.println("Test 5: input that fails validator(Nothing happens)");
        node1.input(1, "H", true);

        System.out.println("Test 6: All persistent input cause no infinite loop(proceed 1-2)");
        node1.input(1, "Hello", true);

        System.out.println("Test 7: wrong input type cause Exceptions(print stack");
        try {
            node1.input(2, "STRING", false);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Test 8: null input should be fine (proceed 1-2)");
        node1.input(2, null, false);

        System.out.println("Test 9: input after all persistent input should trigger proceed(proceed 1-2)");
        node1.input(3, "STRING", false);

        System.out.println("Test 10: after disconnect the second node should no longer be triggered(proceed 1)");
        node1.disconnect(1, node2, 1);
        node1.input(2, Integer.valueOf(1145), false);
    }
}

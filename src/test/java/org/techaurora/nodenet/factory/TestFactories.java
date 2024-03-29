package org.techaurora.nodenet.factory;

import org.techaurora.nodenet.nodes.TestNodeBasic;
import org.techaurora.nodenet.settings.TestSettings;
import org.techaurora.nodenet.settings.TestSettingsHandler;
import org.techaurora.nodenet.utils.TestInputHandler;
import org.techaurora.nodenet.utils.TestOutputHandler;

import java.util.HashMap;
import java.util.Map;

public class TestFactories {

    public static Factory InputHandlerFactory, OutputHandlerFactory, NodeFactory, SettingsFacroty, SettingsHandlerFactory;

    public static void init(){
        Map<String, Class<?>> classSet = new HashMap<>();
        classSet.put("Node", TestNodeBasic.class);
        classSet.put("Settings", TestSettings.class);
        classSet.put("SettingsHandler", TestSettingsHandler.class);
        classSet.put("InputHandler", TestInputHandler.class);
        classSet.put("OutputHandler", TestOutputHandler.class);
        InputHandlerFactory = new InputHandlerFactory();
        InputHandlerFactory.init(classSet);
        OutputHandlerFactory = new OutputHandlerFactory();
        OutputHandlerFactory.init(classSet);
        NodeFactory = new NodeFactory();
        NodeFactory.init(classSet);
        SettingsFacroty = new SettingsFactory();
        SettingsFacroty.init(classSet);
        SettingsHandlerFactory = new SettingsHandlerFactory();
        SettingsHandlerFactory.init(classSet);
    }
}

package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestNodeBasic extends AbstractNode{

    public TestNodeBasic(){
//        this.setInputTypes(Arrays.asList(
//                Integer.class,      //0
//                String.class,       //1
//                Integer.class,      //2
//                String.class));     //3
//        this.setOutputTypes(Arrays.asList(
//                String.class,
//                String.class));
        Validator notNull = new Validator() {
            @Override
            public boolean validate(Object obj) {
                return null != obj;
            }
        };
        Validator lenGeq3 = new Validator() {
            @Override
            public boolean validate(Object obj) {
                if(String.class.isInstance(obj)){
                    return ((String) obj).length() > 3;
                }
                else return false;
            }
        };
        Validator nullAble = new Validator() {
            @Override
            public boolean validate(Object obj) {
                if(null == obj) return true;
                return true;
            }
        };

    }

    /**
     * @param objects
     */
    @Override
    protected void proceed(Map<String, Object> objects) {
        var num1 = (Integer) objects.get(0);
        var str2 = (String) objects.get(1);
        var num3 = (Integer) objects.get(2);
        var str4 = (String) objects.get(3);

        System.out.println("/////  /////");
        System.out.println(num1);
        System.out.println(str2);
        System.out.println(num3);
        System.out.println(str4);
        System.out.println("/////  /////");
        System.out.println();

//        outputHandler.output(0, String.valueOf(str2.length()), false);
//        outputHandler.output(1, str2 + " is STR2!", false);
    }
}

package org.techaurora.nodenet.nodes;

import org.techaurora.nodenet.settings.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestNodeBasic extends AbstractNode{

    public TestNodeBasic(){
        this.setInputTypes(new ArrayList<>(){

        });
        this.setOutputTypes(new ArrayList<>(){

        });
        this.setInputValidators(Arrays.asList(new Validator() {
            @Override
            public boolean validate(Object obj) {
                return false;
            }
        }));
    }

    /**
     * @param objects
     */
    @Override
    protected void proceed(List<Object> objects) {

    }
}

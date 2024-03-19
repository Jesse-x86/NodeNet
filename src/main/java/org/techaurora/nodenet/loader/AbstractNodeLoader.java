package org.techaurora.nodenet.loader;

public class AbstractNodeLoader implements NodeLoader {
    private String nodeURL;
    public AbstractNodeLoader(String nodePath){
        this.nodeURL = nodePath;
    }
}

package com.cqx.pikachu.agent.core;

import java.util.List;

public class PikaPointCut {

    private String className;

    private List<String> methods;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }
}

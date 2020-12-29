package com.cqx.pikachu.agent.core;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class PikaAgentmain {

    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException, InterruptedException {
        inst.addTransformer(PikaTransformerEntry.getInstance(), true);
        inst.retransformClasses(Class.forName("com.cqx.jagent.examples.Person"));
        System.out.println("Agent Main Done");
    }
}

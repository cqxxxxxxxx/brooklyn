package com.cqx.pikachu.agent.core;

import java.lang.instrument.Instrumentation;

public class PikaPremain {

    private static volatile Instrumentation globalInstrumentation;

    /**
     * 1. 指定 Premain-class: com.cqx.stn.jagent.InstrumentAgent
     * 2. JVM启动时 调用main方法前会 执行这个类的premain方法，并且自动把参数注入进来
     * 3. 利用instrumentation来计算对象大小
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(final String agentArgs, final Instrumentation inst) {
        System.out.println("In premain method");
        globalInstrumentation = inst;
        globalInstrumentation.addTransformer(PikaTransformerEntry.getInstance());
    }

    public static long getObjectSize(final Object object) {
        if (globalInstrumentation == null) {
            throw new IllegalStateException("Agent not initialized.");
        }
        return globalInstrumentation.getObjectSize(object);
    }

}

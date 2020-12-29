package com.cqx.pikachu.agent.core;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Optional;

public class PikaTransformerEntry implements ClassFileTransformer {
    public static PikaTransformerEntry getInstance() {
        return INSTANCE;
    }

    private static final PikaTransformerEntry INSTANCE = new PikaTransformerEntry();
    private static final byte[] EMPTY_BYTE_ARRAY = {};

    /**
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer
     * @return
     * @throws IllegalClassFormatException
     */
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        String clazzName = toClassName(className);
        Optional<PikaPointCut> pointCutOpt = PikaPointCuts.tryGet(clazzName);
        if (pointCutOpt.isPresent()) {
            PikaPointCut pikaPointCut = pointCutOpt.get();
            PikaCtHolder pikaCtHolder = new PikaCtHolder(className);
            PikaPlugins.doPlugin(pikaCtHolder);
            return pikaCtHolder.toBytes();
        }
        return EMPTY_BYTE_ARRAY;
    }

    private static String toClassName(final String classFile) {
        return classFile.replace('/', '.');
    }
}

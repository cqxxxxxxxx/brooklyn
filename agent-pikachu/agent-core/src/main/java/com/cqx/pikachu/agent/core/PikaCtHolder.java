package com.cqx.pikachu.agent.core;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * TODO log
 */
public class PikaCtHolder {

    private String className;

    private ClassPool pool;

    private CtClass ctClass;

    public String getClassName() {
        return className;
    }

    public ClassPool getPool() {
        return pool;
    }

    public CtClass getCtClass() {
        return ctClass;
    }

    public PikaCtHolder(String className) {
        this.className = className;
        this.pool = ClassPool.getDefault();
        try {
            this.ctClass = pool.getCtClass(className);
        } catch (NotFoundException e) {
            throw new PikaException(e.getMessage(), e.getCause());
        }
    }

    public byte[] toBytes() {
        try {
            return ctClass.toBytecode();
        } catch (IOException e) {
            throw new PikaException(e.getMessage(), e.getCause());
        } catch (CannotCompileException e) {
            throw new PikaException(e.getMessage(), e.getCause());
        }
    }

}

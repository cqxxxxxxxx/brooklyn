package com.cqx.agent.pikcahu.example;

import com.cqx.pikachu.agent.core.PikaCtHolder;
import com.cqx.pikachu.agent.core.spi.PikaPlugin;
import javassist.*;

public class BlackHolePlugin implements PikaPlugin {
    @Override
    public void pikaPika(PikaCtHolder pikaCtHolder) {
        CtClass ctClass = pikaCtHolder.getCtClass();
        for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
            swallow(ctMethod, ctClass);
        }
    }

    private static void swallow(CtMethod ctMethod, CtClass ctClass) {
        try {
            final CtMethod newCtMethod = CtNewMethod.copy(ctMethod, ctClass, null);
            CtClass returnType = ctMethod.getReturnType();
            if (returnType == CtPrimitiveType.voidType) {
                newCtMethod.setBody("{}");
            } else {
                newCtMethod.setBody("{return null;}");
            }
            ctClass.removeMethod(ctMethod);
            ctClass.addMethod(newCtMethod);
        } catch (NotFoundException | CannotCompileException e) {
            e.printStackTrace();
        }
    }


    private static void backMethod(CtMethod method) {
        method.setName(method.getName() + "Bak");
        method.setModifiers(method.getModifiers()
                & ~java.lang.reflect.Modifier.PUBLIC /* remove public */
                & ~java.lang.reflect.Modifier.PROTECTED /* remove protected */
                | java.lang.reflect.Modifier.PRIVATE /* add private */);
    }
}

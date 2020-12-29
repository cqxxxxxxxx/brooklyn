package com.cqx.pikachu.agent.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * 切点s
 * TODO 初始化
 */
public class PikaPointCuts {

    private static Set<PikaPointCut> cuts = new HashSet<PikaPointCut>();

    public static void init() {

    }

    public static Optional<PikaPointCut> tryGet(String className) {
        for (PikaPointCut cut : cuts) {
            if (Objects.equals(cut.getClassName(), className)) {
                return Optional.of(cut);
            }
        }
        return Optional.empty();
    }
}

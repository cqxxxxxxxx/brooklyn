package com.cqx.pikachu.agent.core;

import com.cqx.pikachu.agent.core.spi.PikaPlugin;
import com.sun.tools.javac.util.ServiceLoader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 插件s
 * TODO init
 */
public class PikaPlugins {
    private static List<PikaPlugin> plugins = new ArrayList<>();
    private static volatile boolean inited = false;

    static {
        init();
    }

    public static void init() {
        ServiceLoader<PikaPlugin> pluginServiceLoader = ServiceLoader.load(PikaPlugin.class);
        final Iterator<PikaPlugin> iterator = pluginServiceLoader.iterator();
        while (iterator.hasNext()) {
            PikaPlugin next = iterator.next();
            plugins.add(next);
        }
        inited = true;
    }

    public static PikaCtHolder doPlugin(PikaCtHolder pikaCtHolder) {
        for (PikaPlugin plugin : plugins) {
            plugin.pikaPika(pikaCtHolder);
        }
        return pikaCtHolder;
    }
}

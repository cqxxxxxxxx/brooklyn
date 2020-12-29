package com.cqx.pikachu.agent.core;

import com.cqx.pikachu.agent.core.spi.PikaPlugin;
import com.sun.tools.javac.util.ServiceLoader;

import java.util.Iterator;
import java.util.List;

/**
 * 插件s
 * TODO init
 */
public class PikaPlugins {
    private static List<PikaPlugin> plugins;
    private static volatile boolean inited = false;

    public static void init() {
        ServiceLoader<PikaPlugin> pluginServiceLoader = ServiceLoader.load(PikaPlugin.class);
        final Iterator<PikaPlugin> iterator = pluginServiceLoader.iterator();
        while (iterator.hasNext()) {
            plugins.add(iterator.next());
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

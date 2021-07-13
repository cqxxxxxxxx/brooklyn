package com.cqx.pikachu.agent.core.spi;

import com.cqx.pikachu.agent.core.PikaCtHolder;
import com.cqx.pikachu.agent.core.util.Logger;

public interface PikaPlugin {
    static Logger log = Logger.getLogger(PikaPlugin.class);
    void pikaPika(PikaCtHolder pikaCtHolder);
}

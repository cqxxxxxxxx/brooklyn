package com.cqx.brooklyn.util;

import com.cqx.brooklyn.Environment;
import com.cqx.brooklyn.command.Command;
import com.cqx.brooklyn.command.EchoCommand;
import com.cqx.brooklyn.command.MockCommand;
import com.cqx.brooklyn.command.OOMCommand;

import java.util.ServiceLoader;

/**
 * SPI机制对命令进行扩展
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/14
 */
public class CommandProvider {
    static {
        Environment environment = Environment.getInstance();
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        serviceLoader.iterator().forEachRemaining(x -> {
            environment.addCommand(x);
        });
        //add default command
        environment.addCommand(new EchoCommand());
        environment.addCommand(new OOMCommand());
        environment.addCommand(new MockCommand());
    }

    public static void reload() {
    }
}

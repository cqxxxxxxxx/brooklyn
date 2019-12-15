package com.cqx.brooklyn;

import com.cqx.brooklyn.command.Command;
import com.cqx.brooklyn.command.Sharable;
import com.cqx.brooklyn.util.CommandProvider;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * http-server的运行环境
 * 1. 采用LazyHolder模式进行懒加载的单例实现
 * 2. 使用ConcurrentHashMap来确保运行时对Command进行添加删操作的线程安全
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/9
 */
public class Environment {
    static {
        //init
        new CommandProvider();
//        try {
//            Thread.currentThread().getContextClassLoader().loadClass("com.cqx.brooklyn.util.CommandProvider");
//        } catch (ClassNotFoundException e) {
//            throw new Error("初始化失败");
//        }
    }

    private static class LazyHolder {
        private static final Environment INSTANCE = new Environment();
    }

    private Map<String, CommandFactory> commandFactoryMap;

    public static Environment getInstance() {
        return LazyHolder.INSTANCE;
    }

    private Environment() {
        commandFactoryMap = new ConcurrentHashMap<>(16);
    }

    public Command getCommand(String name) {
        CommandFactory factory = Optional.ofNullable(commandFactoryMap.get(name))
                .orElseThrow(() -> new IllegalArgumentException("command[" + name + "] not found"));
        return factory.get();
    }

    public void addCommand(Command command) {
        Class clazz = command.getClass();
        boolean sharable = true;
        if (clazz.isAnnotationPresent(Sharable.class)) {
            Sharable anno = (Sharable) clazz.getAnnotation(Sharable.class);
            sharable = anno.value();
        }
        commandFactoryMap.put(command.name(), new CommandFactory(clazz, sharable, command));
    }

    @AllArgsConstructor
    private static class CommandFactory {
        private Class<Command> commandClass;
        private boolean sharable;
        private Command singleton;

        /**
         * 转为运行时异常
         *
         * @return
         */
        @SneakyThrows
        public Command get() {
            return sharable ? singleton : commandClass.newInstance();
        }
    }

}

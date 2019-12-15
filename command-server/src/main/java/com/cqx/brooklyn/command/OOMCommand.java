package com.cqx.brooklyn.command;

import com.cqx.brooklyn.model.Request;
import com.cqx.brooklyn.util.RespUtil;
import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/15
 */
@Slf4j
@Sharable(value = false)
public class OOMCommand implements Command {
    private static BlockingQueue hook = new LinkedBlockingQueue();
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
        private AtomicLong atomicLong = new AtomicLong(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("定时任务" + atomicLong.getAndAdd(1));
            return thread;
        }
    });

    @Override
    public String name() {
        return "oom";
    }

    @Override
    public HttpResponse execute(Request request) {
        Argument argument = request.body(Argument.class);
        executor.schedule(cleanHook(), argument.recoverInSeconds, TimeUnit.SECONDS);
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        long heapMax = memoryMXBean.getHeapMemoryUsage().getMax();
        int part = (int) (heapMax / 10);
        executor.scheduleAtFixedRate(addWeight(part), 1, 5, TimeUnit.SECONDS);
        return RespUtil.success("success");
    }

    private Runnable addWeight(int size) {
        return () -> {
            try {
                hook.put(new byte[size]);
                log.info("memory usage increased");
            } catch (Throwable e) {
                log.error("出现异常{} 不管继续执行", e.getMessage());
            }
        };
    }


    private Runnable cleanHook() {
        return () -> {
            hook.clear();
            hook = null;
            System.gc();
            log.info("hook released, notified system.gc");
        };
    }

    public static class Argument {
        private long recoverInSeconds = 120;
    }


}

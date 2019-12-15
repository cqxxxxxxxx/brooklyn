package com.cqx.brooklyn.command;

/**
 * 表示该command是否线程安全
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/12/15
 */
public @interface Sharable {

    boolean value() default true;

}

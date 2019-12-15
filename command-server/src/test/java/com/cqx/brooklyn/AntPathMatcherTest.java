package com.cqx.brooklyn;

import com.cqx.brooklyn.util.AntPathMatcher;
import org.junit.Test;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/10
 */
public class AntPathMatcherTest {


    @Test
    public void test0() {
        System.out.println(pathMatcher().match("/app/**", "/app/sss"));  //true
        System.out.println(pathMatcher().match("/app/**", "/xxx/app/sss")); //false
        System.out.println(pathMatcher().extractPathWithinPattern("/app/**", "/app/sss/xxx"));  //sss/xxx
        System.out.println(pathMatcher().extractUriTemplateVariables("/app/{name}", "/app/cqx")); //{name=cqx}
    }

    private AntPathMatcher pathMatcher() {
        return new AntPathMatcher();
    }
}

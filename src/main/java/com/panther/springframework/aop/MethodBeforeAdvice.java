package com.panther.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author Gin 琴酒
 * @data 2023/10/31 10:27
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * Callback before a given method is invoked.
     *
     * @param method method being invoked
     * @param args   arguments to the method
     * @param target target of the method invocation. May be <code>null</code>.
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}

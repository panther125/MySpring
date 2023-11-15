package com.panther.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author Gin 琴酒
 * @data 2023/10/30 15:24
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);

}

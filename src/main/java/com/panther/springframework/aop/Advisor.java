package com.panther.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author Gin 琴酒
 * @data 2023/10/31 10:29
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws' advice, etc.
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();

}

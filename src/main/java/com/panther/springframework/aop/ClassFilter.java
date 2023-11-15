package com.panther.springframework.aop;


/**
 * @author Gin 琴酒
 * @data 2023/10/30 15:24
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);

}

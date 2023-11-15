package com.panther.ComponentTest.Bean;

import com.panther.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Gin 琴酒
 * @data 2023/11/1 17:58
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }

}

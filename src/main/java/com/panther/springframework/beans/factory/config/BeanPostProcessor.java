package com.panther.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 14:41
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}

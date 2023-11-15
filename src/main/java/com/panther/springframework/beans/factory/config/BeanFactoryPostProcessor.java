package com.panther.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 14:35
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeanException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;

}

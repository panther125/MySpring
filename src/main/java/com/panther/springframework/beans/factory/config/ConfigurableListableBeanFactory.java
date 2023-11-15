package com.panther.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.beans.factory.ListableBeanFactory;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 14:39
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    void preInstantiateSingletons() throws BeanException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}

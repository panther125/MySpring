package com.panther.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;

import java.util.Map;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 14:47
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeanException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}

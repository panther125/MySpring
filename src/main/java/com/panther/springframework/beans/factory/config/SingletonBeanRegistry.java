package com.panther.springframework.beans.factory.config;

/**
 * @author Gin 琴酒
 * @data 2023/10/23 16:26
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject) throws IllegalAccessException;

    Object getSingleton(String beanName);

}

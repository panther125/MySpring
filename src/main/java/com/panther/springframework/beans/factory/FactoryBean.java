package com.panther.springframework.beans.factory;

/**
 * @author Gin 琴酒
 * @data 2023/10/27 15:28
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}

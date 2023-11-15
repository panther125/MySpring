package com.panther.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;

/**
 * @author Gin 琴酒
 * @data 2023/10/23 16:56
 */
public interface BeanFactory {

     Object getBean(String name) throws BeanException;

     Object getBean(String name, Object... args) throws BeanException;

     <T> T getBean(String name, Class<T> requiredType) throws BeanException;

     <T> T getBean(Class<T> requiredType) throws BeanException;

}

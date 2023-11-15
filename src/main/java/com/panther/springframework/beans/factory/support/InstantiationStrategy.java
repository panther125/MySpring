package com.panther.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author Gin 琴酒
 * @data 2023/10/23 17:45
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition bd, String beanName, Constructor<?> ctor, Object... args) throws BeanException;

}

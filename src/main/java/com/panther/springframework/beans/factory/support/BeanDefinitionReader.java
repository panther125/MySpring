package com.panther.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.core.io.Resource;
import com.panther.springframework.core.io.ResourceLoader;

/**
 * @author Gin 琴酒
 * @data 2023/10/24 17:48
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinitions(Resource... resources) throws BeanException;

    void loadBeanDefinitions(String location) throws BeanException;

    void loadBeanDefinitions(String... locations) throws BeanException;

}

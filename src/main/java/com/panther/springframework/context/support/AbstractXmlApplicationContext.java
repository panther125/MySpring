package com.panther.springframework.context.support;

import com.panther.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.panther.springframework.beans.factory.support.XmlBeanDefinitionReader;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 16:53
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}

package com.panther.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.beans.factory.ApplicationContextAware;
import com.panther.springframework.beans.factory.config.BeanPostProcessor;
import com.panther.springframework.context.ApplicationContext;

/**
 * @author Gin 琴酒
 * @data 2023/10/26 17:36
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware)bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}

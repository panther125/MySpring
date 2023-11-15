package com.panther.springframework.beans.factory.support;

import com.panther.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gin 琴酒
 * @data 2023/10/27 15:30
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    protected Object getCacheObjectForFactoryBean(String beanName) {
        Object beanCache = this.factoryBeanObjectCache.get(beanName);
        return beanCache != NULL_OBJECT ? beanCache : null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {

        if (factory.isSingleton()) {
            Object beanCache = this.factoryBeanObjectCache.get(beanName);
            if (beanCache == null) {
                beanCache = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (beanCache != null ? beanCache : NULL_OBJECT));
            }
            return (beanCache != NULL_OBJECT ? beanCache : null);
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {

        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.panther.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.lang.Assert;
import com.panther.springframework.beans.factory.DisposableBean;
import com.panther.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gin 琴酒
 * @data 2023/10/23 16:27
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    // todo 三级缓存 和 实现单例 bean的一些集合
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    // 如果spring 的bean实现了 singleton 单例模式 会用 一个 set 集合记录 BeanName
    private final Set<String> registeredSingletons = new LinkedHashSet<>(256);

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) throws IllegalAccessException {
        Assert.notNull(beanName, "Bean name must not be null");
        Assert.notNull(singletonObject, "Singleton object must not be null");

        synchronized (this.singletonObjects){
            if(singletonObjects.containsKey(beanName)){
                throw new IllegalAccessException("不能注册这个bean"+beanName+",因为bean 已经存在");
            }
            addSingleton(beanName,singletonObject);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        Assert.notNull(beanName , "beanName不能为空");
        return singletonObjects.getOrDefault(beanName,null);
    }


    protected void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
            //this.singletonFactories.remove(beanName); // 三级缓存 beanDefinition 和 factory
            //this.earlySingletonObjects.remove(beanName); // 一级缓存 存放 早期 Bean
            this.registeredSingletons.add(beanName); // 防止Bean Name重复
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeanException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}

package com.panther.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.beans.factory.FactoryBean;
import com.panther.springframework.beans.factory.config.BeanDefinition;
import com.panther.springframework.beans.factory.config.BeanPostProcessor;
import com.panther.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.panther.springframework.utils.ClassUtils;
import com.panther.springframework.utils.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/10/23 16:53
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    /**
     * String resolvers to apply e.g. to annotation attribute values
     */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeanException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object instance = getSingleton(name);
        if (instance != null) {
            return (T) instance;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);

        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCacheObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}

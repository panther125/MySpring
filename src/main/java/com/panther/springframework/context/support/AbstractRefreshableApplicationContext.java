package com.panther.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.panther.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 15:15
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected final void refreshBeanFactory() throws BeanException {
        beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);


}

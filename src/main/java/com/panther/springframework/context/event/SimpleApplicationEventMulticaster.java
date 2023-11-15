package com.panther.springframework.context.event;

import com.panther.springframework.beans.factory.BeanFactory;
import com.panther.springframework.context.ApplicationEvent;

/**
 * @author Gin 琴酒
 * @data 2023/10/27 16:32
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        getApplicationListeners(event).forEach(e -> e.onApplicationEvent(event));
    }
}

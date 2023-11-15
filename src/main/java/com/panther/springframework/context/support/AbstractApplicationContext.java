package com.panther.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.panther.springframework.beans.factory.config.BeanPostProcessor;
import com.panther.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.panther.springframework.context.ApplicationEvent;
import com.panther.springframework.context.ApplicationListener;
import com.panther.springframework.context.ConfigurableApplicationContext;
import com.panther.springframework.context.event.ApplicationEventMulticaster;
import com.panther.springframework.context.event.ContextClosedEvent;
import com.panther.springframework.context.event.ContextRefreshedEvent;
import com.panther.springframework.context.event.SimpleApplicationEventMulticaster;
import com.panther.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 14:51
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private final Object startupShutdownMonitor;

    private Thread shutdownHook;

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    public AbstractApplicationContext() {
        this.startupShutdownMonitor = new Object();
    }

    @Override
    public void refresh() throws BeanException {
        synchronized(this.startupShutdownMonitor){
            // 1. 创建 BeanFactory，并加载 BeanDefinition
            refreshBeanFactory();

            // 2. 获取 BeanFactory
            ConfigurableListableBeanFactory beanFactory = getBeanFactory();

            // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
            beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

            // 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
            invokeBeanFactoryPostProcessors(beanFactory);

            // 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
            registerBeanPostProcessors(beanFactory);

            // 6. 初始化事件发布者
            initApplicationEventMulticaster();

            // 7. 注册事件监听器
            registerListeners();

            // 8. 提前实例化单例Bean对象
            beanFactory.preInstantiateSingletons();

            // 9. 发布容器刷新完成事件
            finishRefresh();
        }
    }

    private void initApplicationEventMulticaster()  {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        try {
            beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();

        applicationListeners.forEach(applicationEventMulticaster::addApplicationListener);
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void registerShutdownHook() {
        if (this.shutdownHook == null) {
            this.shutdownHook = new Thread("SpringContextShutdownHook") {
                public void run() {
                    synchronized (AbstractApplicationContext.this.startupShutdownMonitor) {
                        AbstractApplicationContext.this.close();
                    }
                }
            };
            Runtime.getRuntime().addShutdownHook(this.shutdownHook);
        }
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }

    protected abstract void refreshBeanFactory() throws BeanException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeanException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        return getBeanFactory().getBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(requiredType);
    }
}

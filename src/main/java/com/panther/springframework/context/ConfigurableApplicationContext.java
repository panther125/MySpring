package com.panther.springframework.context;

import cn.hutool.core.bean.BeanException;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 14:49
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh() throws BeanException;

    void registerShutdownHook();

    void close();
}

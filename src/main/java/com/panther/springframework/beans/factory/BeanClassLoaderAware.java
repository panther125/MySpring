package com.panther.springframework.beans.factory;

/**
 * @author Gin 琴酒
 * @data 2023/10/26 17:34
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}

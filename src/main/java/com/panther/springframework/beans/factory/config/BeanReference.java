package com.panther.springframework.beans.factory.config;

/**
 * @author Gin 琴酒
 * @data 2023/10/24 15:54
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}

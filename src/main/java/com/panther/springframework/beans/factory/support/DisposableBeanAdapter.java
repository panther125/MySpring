package com.panther.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import com.panther.springframework.beans.factory.DisposableBean;
import com.panther.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author Gin 琴酒
 * @data 2023/10/26 14:32
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }


    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }

        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {

            Method method = bean.getClass().getMethod(this.destroyMethodName);
            if(method == null){
                throw new BeanException("destroy method is not find");
            }
            method.invoke(bean);
        }
    }
}

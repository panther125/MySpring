package com.panther.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.lang.Assert;
import com.panther.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Gin 琴酒
 * @data 2023/10/23 17:47
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition bd, String beanName, Constructor<?> ctor, Object... args) throws BeanException {
        try {
            if (null != ctor) {
                Class<?>[] parameterTypes = ctor.getParameterTypes();
                Assert.isTrue(args.length <= parameterTypes.length, "Can't specify more arguments than constructor parameters");
                return ctor.newInstance(args);
            } else {
                return bd.getBeanClass().getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException e) {
            throw new BeanException(ctor + "Is it an abstract class?");
        } catch (IllegalAccessException e) {
            throw new BeanException(ctor + "Is the constructor accessible?");
        } catch (InvocationTargetException e) {
            throw new BeanException(ctor + "Constructor threw exception");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("不存在的构造函数！");
        }

    }
}

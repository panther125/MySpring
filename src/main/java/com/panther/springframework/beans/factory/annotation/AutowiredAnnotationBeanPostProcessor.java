package com.panther.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import com.panther.springframework.beans.factory.BeanFactory;
import com.panther.springframework.beans.factory.BeanFactoryAware;
import com.panther.springframework.beans.factory.PropertyValues;
import com.panther.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.panther.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.panther.springframework.utils.ClassUtils;

import java.lang.reflect.Field;

/**
 * 扫描自定义注解
 *
 * @author Gin 琴酒
 * @data 2023/11/1 10:39
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {

        // 1. 处理注解 @Value
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        // 填充字段
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Value Annovalue = field.getAnnotation(Value.class);
            if (Annovalue != null) {
                String value = Annovalue.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 2. 处理注解 @Autowired
        for (Field field : declaredFields) {

            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                Class<?> type = field.getType();
                Qualifier annQualifier = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                // 如果存在 Qualifier 先根据 name 再根据 type
                if (annQualifier != null) {
                    String QBeanName = annQualifier.value();
                    dependentBean = beanFactory.getBean(QBeanName);
                } else {
                    dependentBean = beanFactory.getBean(type);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }

        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        return null;
    }
}

package com.panther.springframework.aop.framework.autoproxy;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.aop.AdvisedSupport;
import com.panther.springframework.aop.Advisor;
import com.panther.springframework.aop.ClassFilter;
import com.panther.springframework.aop.TargetSource;
import com.panther.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.panther.springframework.aop.framework.ProxyFactory;
import com.panther.springframework.beans.factory.BeanFactory;
import com.panther.springframework.beans.factory.BeanFactoryAware;
import com.panther.springframework.beans.factory.PropertyValues;
import com.panther.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.panther.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gin 琴酒
 * @data 2023/10/31 10:44
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {

        //List<Object> collect = advisors.stream()
        //        .filter(e -> e.getPointcut().getClassFilter().matches(beanClass))
        //        .map(item -> {
        //            AdvisedSupport advisedSupport = new AdvisedSupport();
        //
        //            TargetSource targetSource = null;
        //            try {
        //                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //            }
        //            advisedSupport.setTargetSource(targetSource);
        //            advisedSupport.setMethodInterceptor((MethodInterceptor) item.getAdvice());
        //            advisedSupport.setMethodMatcher(item.getPointcut().getMethodMatcher());
        //            advisedSupport.setProxyTargetClass(false);
        //
        //            return new ProxyFactory(advisedSupport).getProxy();
        //        }).limit(1).collect(Collectors.toList());

        return null;
    }


    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if (isInfrastructureClass(bean.getClass())) return bean;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 过滤匹配类
            if (!classFilter.matches(bean.getClass())) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            // 返回代理对象
            return new ProxyFactory(advisedSupport).getProxy();
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {
        return pvs;
    }
}

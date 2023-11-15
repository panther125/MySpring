package com.panther.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.panther.springframework.beans.factory.config.BeanDefinition;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Gin 琴酒
 * @data 2023/10/31 16:13
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 通过这个方法就可以扫描到所有 @Component 注解的 Bean 对象了。
     * @param basePackage 包路径
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}

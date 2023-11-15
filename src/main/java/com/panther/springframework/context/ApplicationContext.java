package com.panther.springframework.context;

import com.panther.springframework.beans.factory.HierarchicalBeanFactory;
import com.panther.springframework.beans.factory.ListableBeanFactory;
import com.panther.springframework.core.io.ResourceLoader;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 14:45
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}

package com.panther.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;
import com.panther.springframework.context.ApplicationContext;

/**
 * @author Gin 琴酒
 * @data 2023/10/26 17:35
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeanException;

}

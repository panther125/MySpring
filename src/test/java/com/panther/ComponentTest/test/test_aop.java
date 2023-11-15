package com.panther.ComponentTest.test;

import com.panther.ComponentTest.Bean.IUserService;
import com.panther.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author Gin 琴酒
 * @data 2023/11/1 17:40
 */
public class test_aop {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}

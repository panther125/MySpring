package com.panther.springframework.beans.factory;

/**
 * @author Gin 琴酒
 * @data 2023/10/25 23:47
 */
public interface DisposableBean {

    void destroy() throws Exception;

}

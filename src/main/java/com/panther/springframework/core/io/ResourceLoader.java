package com.panther.springframework.core.io;

/**
 * @author Gin 琴酒
 * @data 2023/10/24 17:42
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}

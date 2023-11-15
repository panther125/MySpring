package com.panther.springframework.beans.factory;

import cn.hutool.core.lang.Assert;

/**
 * @author Gin 琴酒
 * @data 2023/10/24 15:31
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        Assert.notBlank(name, "Name must not be null");
        Assert.notNull(value, "value must not be null");
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}

package com.panther.springframework.beans.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/10/24 15:34
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    Iterator<PropertyValue> iterator() {
        return Arrays.asList(getPropertyValues()).iterator();
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue pv : getPropertyValues()){
            if(pv.getName().equals(propertyName)) return pv;
        }
        return null;
    }
}

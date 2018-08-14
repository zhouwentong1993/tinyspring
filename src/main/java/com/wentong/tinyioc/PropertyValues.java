package com.wentong.tinyioc;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private List<PropertyValue> propertyValueList = new ArrayList<>();


    public void addProperty(PropertyValue propertyValue) {
        if (propertyValueList.contains(propertyValue)) {
            propertyValueList.set(propertyValueList.indexOf(propertyValue), propertyValue);
        }
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}

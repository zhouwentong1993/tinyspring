package com.wentong.tinyioc.factory;

import com.wentong.tinyioc.BeanDefinition;
import com.wentong.tinyioc.PropertyValue;
import com.wentong.tinyioc.utils.CommonUtils;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    public Object doCreateBeanDefinition(BeanDefinition beanDefinition) {
        try {
            Object bean = beanDefinition.getBeanClass().newInstance();
            applyPropertyValues(bean, beanDefinition);
            return bean;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("无法创建类：" + beanDefinition.getBeanClassName() + " 的实例");
        }
    }

    private void applyPropertyValues(Object bean,BeanDefinition beanDefinition) {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            try {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, propertyValue.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalStateException(CommonUtils.getLogString("bean：{} 加载属性：{} 失败，失败原因是：{}", bean, propertyValue, e.getMessage()));
            }
        }
    }
}

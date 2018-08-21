package com.wentong.tinyioc.factory;

import com.wentong.tinyioc.BeanDefinition;
import com.wentong.tinyioc.BeanReference;
import com.wentong.tinyioc.PropertyValue;
import com.wentong.tinyioc.utils.CommonUtils;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    public Object doCreateBeanDefinition(BeanDefinition beanDefinition) {
        try {
            Object bean = beanDefinition.getBeanClass().newInstance();
            // 跟之前相比增加了这一行，因为之前注入的都是简单的属性，所以不会有重复注入的问题
            // 但是这一次升级注入的是实体属性，循环注入时会有问题
            beanDefinition.setBean(bean);
            applyPropertyValues(bean, beanDefinition);
            return bean;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("无法创建类：" + beanDefinition.getBeanClassName() + " 的实例");
        }
    }

    /**
     * 给对应的 bean 赋属性值
     *
     * @param bean           待赋值的属性
     * @param beanDefinition beanDefinition 里的属性集合数据
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            try {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                Object value = propertyValue.getValue();
                // 如果类中的属性是 BeanReference 类型，代表属性是对象类型
                // 需要通过 BeanFactory 的 getBean 方法获取到 bean，然后设置
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    Object referenceBean = getBean(beanReference.getName());
                    declaredField.set(bean, referenceBean);
                } else {
                    declaredField.set(bean, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalStateException(CommonUtils.getLogString("bean：{} 加载属性：{} 失败，失败原因是：{}", bean, propertyValue, e.getMessage()));
            }
        }
    }
}

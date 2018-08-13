package com.wentong.tinyioc;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    public Object doCreateBeanDefinition(BeanDefinition beanDefinition) {
        try {
            return beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("无法创建类：" + beanDefinition.getBeanClassName() + " 的实例");
        }
    }
}

package com.wentong.tinyioc;

/**
 * 抽象工厂
 */
public interface BeanFactory {

    Object getBean(String beanName);

    void registerBean(String beanName, BeanDefinition beanDefinition);
}

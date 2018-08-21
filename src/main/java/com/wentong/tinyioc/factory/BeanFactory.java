package com.wentong.tinyioc.factory;

/**
 * 抽象工厂
 */
public interface BeanFactory {

    Object getBean(String beanName);
}

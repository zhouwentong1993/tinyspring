package com.wentong.tinyioc;

import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory{
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

    @Override
    public void registerBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = doCreateBeanDefinition(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    public abstract Object doCreateBeanDefinition(BeanDefinition beanDefinition);
}

package com.wentong.tinyioc.factory;

import com.wentong.tinyioc.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("指定 beanName：" + beanName + "获取 BeanDefinition 失败");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            return doCreateBeanDefinition(beanDefinition);
        } else {
            return bean;
        }
    }

    @Override
    public void registerBean(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
        beanDefinitionNames.add(beanName);
    }

    /**
     * 预初始化所有 bean
     */
    public void preInstantiateSingletons() {
        for (String beanDefinitionName : beanDefinitionNames) {
            getBean(beanDefinitionName);
        }
    }

    public abstract Object doCreateBeanDefinition(BeanDefinition beanDefinition);
}

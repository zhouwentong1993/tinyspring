package com.wentong.tinyioc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂
 */
public class BeanFactory {
    private ConcurrentHashMap<String,BeanDefinition> beanMap = new ConcurrentHashMap();

    public Object getBean(String beanName) {
        return beanMap.get(beanName).getBean();
    }

    public void registerBean(String beanName, BeanDefinition beanDefinition) {
        if (beanMap.containsKey(beanName)) {
            throw new IllegalArgumentException("指定的 beanName：" + beanName + "已存在");
        }
        beanMap.put(beanName, beanDefinition);
    }
}

package com.wentong.tinyioc.context;

import com.wentong.tinyioc.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext {
    public AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() {

    }

    @Override
    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }
}

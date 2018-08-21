package com.wentong.tinyioc.context;

import com.wentong.tinyioc.factory.AbstractBeanFactory;
import com.wentong.tinyioc.factory.AutowireCapableBeanFactory;
import com.wentong.tinyioc.io.URLResourceLoader;
import com.wentong.tinyioc.xml.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) {
        this(new AutowireCapableBeanFactory(), configLocation);
    }

    public ClassPathXmlApplicationContext(AbstractBeanFactory beanFactory, String configLocation) {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new URLResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition(configLocation);
        xmlBeanDefinitionReader.getRegistry().forEach(beanFactory::registerBeanDefinition);
    }
}

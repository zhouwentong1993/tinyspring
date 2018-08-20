package com.wentong.tinyioc;

import com.wentong.tinyioc.factory.AbstractBeanFactory;
import com.wentong.tinyioc.factory.AutowireCapableBeanFactory;
import com.wentong.tinyioc.factory.BeanFactory;
import com.wentong.tinyioc.io.ResourceLoader;
import com.wentong.tinyioc.io.URLResourceLoader;
import com.wentong.tinyioc.xml.XmlBeanDefinitionReader;
import org.junit.Test;

public class BeanFactoryTest {

//    @Test
//    public void testBeanFactory() {
//        BeanDefinition beanDefinition = new BeanDefinition();
//        beanDefinition.setBean(new com.wentong.tinyioc.HelloService());
//        BeanFactory beanFactory = new BeanFactory();
//        beanFactory.registerBean("helloService", beanDefinition);
//
//        com.wentong.tinyioc.HelloService helloService = (com.wentong.tinyioc.HelloService)beanFactory.getBean("helloService");
//        helloService.sayHello();
//    }

    @Test
    public void testBeanFactory() {

        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.wentong.tinyioc.HelloService");
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue = new PropertyValue("text","haha");
        propertyValues.getPropertyValueList().add(propertyValue);
        beanDefinition.setPropertyValues(propertyValues);
        beanFactory.registerBean("helloService", beanDefinition);


        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();

    }

    @Test
    public void testXml() throws Exception{
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new URLResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition("tinyioc.xml");

        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
        xmlBeanDefinitionReader.getRegistry().forEach(beanFactory::registerBean);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }

    /**
     * 测试延迟初始化
     */
    @Test
    public void testLazyInit() {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new URLResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition("tinyioc.xml");

        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        xmlBeanDefinitionReader.getRegistry().forEach(beanFactory::registerBean);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}

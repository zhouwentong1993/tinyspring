package com.wentong.tinyioc;

import com.wentong.tinyioc.factory.AutowireCapableBeanFactory;
import com.wentong.tinyioc.factory.BeanFactory;
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
        PropertyValue propertyValue = new PropertyValue();
        propertyValue.setName("text");
        propertyValue.setValue("haaha");
        propertyValues.getPropertyValueList().add(propertyValue);
        beanDefinition.setPropertyValues(propertyValues);
        beanFactory.registerBean("helloService", beanDefinition);


        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();

    }
}

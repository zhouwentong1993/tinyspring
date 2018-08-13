package com.wentong.tinyioc;

import com.wentong.tinyioc.AutowireCapableBeanFactory;
import com.wentong.tinyioc.BeanDefinition;
import com.wentong.tinyioc.BeanFactory;
import org.junit.Test;

import javax.swing.text.DefaultEditorKit;

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
        beanFactory.registerBean("helloService", beanDefinition);

        System.out.println(beanFactory.getBean("helloService"));

    }
}

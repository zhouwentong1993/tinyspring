package com.wentong.tinyioc;

import com.wentong.tinyioc.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloService helloService = (HelloService) classPathXmlApplicationContext.getBean("helloService");
        helloService.sayHello();
    }
}

import com.wentong.tinyioc.BeanDefinition;
import com.wentong.tinyioc.BeanFactory;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void testBeanFactory() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBean(new HelloService());
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("helloService", beanDefinition);

        HelloService helloService = (HelloService)beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}

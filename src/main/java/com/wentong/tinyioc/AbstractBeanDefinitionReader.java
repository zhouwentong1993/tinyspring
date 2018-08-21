package com.wentong.tinyioc;

import com.wentong.tinyioc.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    // 这个是用来承接 xml 或者注解解析文件得到的结果的，放到 Abstract 类里面，可以供底下所有的子类使用
    private Map<String,BeanDefinition> registry;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.registry = new HashMap<>();
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}

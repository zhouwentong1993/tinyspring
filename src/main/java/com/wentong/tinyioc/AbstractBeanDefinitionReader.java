package com.wentong.tinyioc;

import com.wentong.tinyioc.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    // 这个东西到底有什么含义，不太理解。
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

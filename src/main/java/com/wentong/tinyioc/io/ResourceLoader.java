package com.wentong.tinyioc.io;


/**
 * 资源加载接口，返回 Spring 的资源
 */
public interface ResourceLoader {

    Resource getResource(String location);
}

package com.wentong.tinyioc.io;

import java.net.URL;

/**
 * url 资源加载
 */
public class URLResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new URLResource(resource);
    }
}

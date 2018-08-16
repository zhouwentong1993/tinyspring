package com.wentong.tinyioc.io;

import java.io.InputStream;

/**
 * 用于定位资源的接口，通过返回是 InputStream，这样可以满足所有的格式
 */
public interface Resource {

    InputStream getInputStream();
}

package com.wentong.tinyioc.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLResource implements Resource {

    private URL url;

    public URLResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() {
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            return urlConnection.getInputStream();
        } catch (IOException e) {
            throw new IllegalStateException("获取 URLResource 流失败，失败原因是：" + e);
        }
    }
}

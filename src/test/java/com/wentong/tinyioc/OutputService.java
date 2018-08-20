package com.wentong.tinyioc;

import java.util.Objects;

public class OutputService {
    private HelloService helloService;

    public void output(String text) {
        Objects.requireNonNull(helloService);
        System.out.println(text);
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}

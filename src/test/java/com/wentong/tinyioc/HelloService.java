package com.wentong.tinyioc;

public class HelloService {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void sayHello() {
        System.out.println("hello world" + text);

    }
}

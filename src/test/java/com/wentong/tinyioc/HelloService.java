package com.wentong.tinyioc;

public class HelloService {

    private String text;

    private OutputService outputService;

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void sayHello() {
        outputService.output(text);
    }
}

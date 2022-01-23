package com.example.demo.template_method.example2;

public class PlusProcessor extends FileProcessor {

    public PlusProcessor(String path) {
        super(path);
    }

    @Override
    protected int getResult(int result, int number) {
        return result + number;
    }
}

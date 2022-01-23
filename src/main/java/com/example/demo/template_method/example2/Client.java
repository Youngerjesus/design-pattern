package com.example.demo.template_method.example2;

public class Client {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new PlusProcessor("number.txt");
        int result = fileProcessor.process();
        System.out.println(result);
    }
}

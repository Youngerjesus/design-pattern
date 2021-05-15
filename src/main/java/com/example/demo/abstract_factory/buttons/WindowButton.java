package com.example.demo.abstract_factory.buttons;

public class WindowButton implements Button {
    @Override
    public void paint() {
        System.out.println("You have created WindowsButton");
    }
}

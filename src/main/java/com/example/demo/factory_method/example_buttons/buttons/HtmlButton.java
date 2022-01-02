package com.example.demo.factory_method.example_buttons.buttons;

public class HtmlButton implements Button{

    @Override
    public void render() {
        System.out.println("<button> Test Button </button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - 'Hello World'");
    }
}

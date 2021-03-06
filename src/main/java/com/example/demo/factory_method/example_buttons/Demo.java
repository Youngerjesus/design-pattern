package com.example.demo.factory_method.example_buttons;

import com.example.demo.factory_method.example_buttons.factory.Dialog;
import com.example.demo.factory_method.example_buttons.factory.HtmlDialog;
import com.example.demo.factory_method.example_buttons.factory.WindowDialog;

public class Demo {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    private static void configure() {
        if(System.getProperty("os.name").equals("Window 10")){
            dialog = new WindowDialog();
        } else{
            dialog = new HtmlDialog();
        }
    }

    private static void runBusinessLogic() {
        dialog.renderWindow();
    }
}

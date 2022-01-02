package com.example.demo.factory_method.example_buttons.factory;

import com.example.demo.factory_method.example_buttons.buttons.Button;
import com.example.demo.factory_method.example_buttons.buttons.WindowButton;

public class WindowDialog extends Dialog {

    @Override
    protected Button createButton() {
        return new WindowButton();
    }
}

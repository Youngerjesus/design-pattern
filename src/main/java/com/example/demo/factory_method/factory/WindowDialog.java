package com.example.demo.factory_method.factory;

import com.example.demo.factory_method.buttons.Button;
import com.example.demo.factory_method.buttons.WindowButton;

public class WindowDialog extends Dialog {

    @Override
    protected Button createButton() {
        return new WindowButton();
    }
}

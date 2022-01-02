package com.example.demo.factory_method.example_buttons.factory;

import com.example.demo.factory_method.example_buttons.buttons.Button;
import com.example.demo.factory_method.example_buttons.buttons.HtmlButton;

public class HtmlDialog extends Dialog{

    @Override
    protected Button createButton() {
        return new HtmlButton();
    }
}

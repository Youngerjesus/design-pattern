package com.example.demo.factory_method.factory;

import com.example.demo.factory_method.buttons.Button;
import com.example.demo.factory_method.buttons.HtmlButton;

public class HtmlDialog extends Dialog{

    @Override
    protected Button createButton() {
        return new HtmlButton();
    }
}

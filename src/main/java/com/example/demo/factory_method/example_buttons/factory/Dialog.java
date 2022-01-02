package com.example.demo.factory_method.example_buttons.factory;

import com.example.demo.factory_method.example_buttons.buttons.Button;

public abstract class Dialog {

    public void renderWindow(){
        // other code

        Button button = createButton();
        button.render();
    }

    protected abstract Button createButton();
}

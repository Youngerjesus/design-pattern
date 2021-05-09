package com.example.demo.factory_method.factory;

import com.example.demo.factory_method.buttons.Button;

public abstract class Dialog {

    public void renderWindow(){
        // other code

        Button button = createButton();
        button.render();
    }

    protected abstract Button createButton();
}

package com.example.demo.abstract_factory.app;

import com.example.demo.abstract_factory.buttons.Button;
import com.example.demo.abstract_factory.checkboxes.CheckBox;
import com.example.demo.abstract_factory.factories.GUIFactory;

public class Application {
    private Button button;
    private CheckBox checkBox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkBox = factory.createCheckBox();
    }

    public void paint(){
        button.paint();
        checkBox.paint();
    }
}

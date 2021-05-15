package com.example.demo.abstract_factory.factories;

import com.example.demo.abstract_factory.buttons.Button;
import com.example.demo.abstract_factory.buttons.MacOSButton;
import com.example.demo.abstract_factory.checkboxes.CheckBox;
import com.example.demo.abstract_factory.checkboxes.MacOSCheckBox;

public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacOSCheckBox();
    }
}

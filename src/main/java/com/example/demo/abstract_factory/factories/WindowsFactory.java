package com.example.demo.abstract_factory.factories;

import com.example.demo.abstract_factory.buttons.Button;
import com.example.demo.abstract_factory.buttons.WindowButton;
import com.example.demo.abstract_factory.checkboxes.CheckBox;
import com.example.demo.abstract_factory.checkboxes.WindowsCheckBox;

public class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}

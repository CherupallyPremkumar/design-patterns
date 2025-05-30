package com.designpatterns.creational.abstractfactory.light;

import com.designpatterns.creational.abstractfactory.*;

public class LightThemeFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public TextBox createTextBox() {
        return new LightTextBox();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }

    @Override
    public String getTheme() {
        return "Light Theme";
    }
}
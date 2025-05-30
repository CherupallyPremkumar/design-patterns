package com.designpatterns.creational.abstractfactory.dark;

import com.designpatterns.creational.abstractfactory.*;

public class DarkThemeFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public TextBox createTextBox() {
        return new DarkTextBox();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }

    @Override
    public String getTheme() {
        return "Dark Theme";
    }
}
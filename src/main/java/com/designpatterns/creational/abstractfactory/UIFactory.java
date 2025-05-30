package com.designpatterns.creational.abstractfactory;

/**
 * Abstract Factory interface for creating UI components
 */
public interface UIFactory {
    Button createButton();

    TextBox createTextBox();

    Checkbox createCheckbox();

    String getTheme();
}
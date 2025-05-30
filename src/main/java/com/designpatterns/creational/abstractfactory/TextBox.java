package com.designpatterns.creational.abstractfactory;

/**
 * Interface for text box components
 */
public interface TextBox extends UIComponent {
    void setText(String text);

    String getText();

    int getMaxLength();

    void setMaxLength(int maxLength);
}
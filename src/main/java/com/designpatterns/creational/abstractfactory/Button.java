package com.designpatterns.creational.abstractfactory;

/**
 * Interface for button components
 */
public interface Button extends UIComponent {
    void click();

    boolean isEnabled();

    void setEnabled(boolean enabled);
}
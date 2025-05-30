package com.designpatterns.creational.abstractfactory;

/**
 * Interface for checkbox components
 */
public interface Checkbox extends UIComponent {
    boolean isChecked();

    void setChecked(boolean checked);

    String getLabel();

    void setLabel(String label);
}
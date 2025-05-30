package com.designpatterns.creational.abstractfactory.light;

import com.designpatterns.creational.abstractfactory.Checkbox;

public class LightCheckbox implements Checkbox {
    private boolean checked = false;
    private String label = "";

    @Override
    public void render() {
        System.out.println("Rendering Light Theme Checkbox: [" + (checked ? "X" : " ") + "] " + label);
    }

    @Override
    public String getType() {
        return "Light Checkbox";
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }
}
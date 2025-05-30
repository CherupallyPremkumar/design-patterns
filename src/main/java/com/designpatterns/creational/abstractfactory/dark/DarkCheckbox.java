package com.designpatterns.creational.abstractfactory.dark;

import com.designpatterns.creational.abstractfactory.Checkbox;

public class DarkCheckbox implements Checkbox {
    private boolean checked = false;
    private String label = "";

    @Override
    public void render() {
        System.out.println("Rendering Dark Theme Checkbox: [" + (checked ? "X" : " ") + "] " + label);
    }

    @Override
    public String getType() {
        return "Dark Checkbox";
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
package com.designpatterns.creational.abstractfactory.dark;

import com.designpatterns.creational.abstractfactory.Button;

public class DarkButton implements Button {
    private boolean enabled = true;

    @Override
    public void render() {
        System.out.println("Rendering Dark Theme Button" + (enabled ? "" : " (disabled)"));
    }

    @Override
    public String getType() {
        return "Dark Button";
    }

    @Override
    public void click() {
        if (enabled) {
            System.out.println("Dark Button clicked");
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
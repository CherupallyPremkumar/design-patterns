package com.designpatterns.creational.abstractfactory.light;

import com.designpatterns.creational.abstractfactory.Button;

public class LightButton implements Button {
    private boolean enabled = true;

    @Override
    public void render() {
        System.out.println("Rendering Light Theme Button" + (enabled ? "" : " (disabled)"));
    }

    @Override
    public String getType() {
        return "Light Button";
    }

    @Override
    public void click() {
        if (enabled) {
            System.out.println("Light Button clicked");
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
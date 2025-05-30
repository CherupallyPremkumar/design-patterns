package com.designpatterns.creational.abstractfactory.light;

import com.designpatterns.creational.abstractfactory.TextBox;

public class LightTextBox implements TextBox {
    private String text = "";
    private int maxLength = 100;

    @Override
    public void render() {
        System.out.println("Rendering Light Theme TextBox: " + text);
    }

    @Override
    public String getType() {
        return "Light TextBox";
    }

    @Override
    public void setText(String text) {
        this.text = text.substring(0, Math.min(text.length(), maxLength));
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int getMaxLength() {
        return maxLength;
    }

    @Override
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        if (text.length() > maxLength) {
            text = text.substring(0, maxLength);
        }
    }
}
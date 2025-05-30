package com.designpatterns.creational.abstractfactory;

/**
 * Base interface for all UI components
 */
public interface UIComponent {
    void render();

    String getType();
}
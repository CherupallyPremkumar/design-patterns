package com.designpatterns.structural.proxy;

/**
 * Subject interface defining image operations
 * Both the RealImage and ImageProxy will implement this interface
 */
public interface Image {
    void display();

    String getFilename();

    int getWidth();

    int getHeight();

    boolean isLoaded();
}
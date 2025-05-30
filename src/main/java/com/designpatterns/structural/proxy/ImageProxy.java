package com.designpatterns.structural.proxy;

/**
 * Proxy class that controls access to RealImage
 * Implements lazy loading and can add access control or logging
 */
public class ImageProxy implements Image {
    private final String filename;
    private final int width;
    private final int height;
    private RealImage realImage;
    private int accessCount;

    public ImageProxy(String filename, int width, int height) {
        this.filename = filename;
        this.width = width;
        this.height = height;
        this.accessCount = 0;
    }

    @Override
    public void display() {
        accessCount++;
        System.out.println("Access count for " + filename + ": " + accessCount);

        if (realImage == null) {
            realImage = new RealImage(filename, width, height);
        }
        realImage.display();
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isLoaded() {
        return realImage != null && realImage.isLoaded();
    }

    public void unloadImage() {
        if (realImage != null) {
            System.out.println("Unloading image: " + filename);
            realImage = null;
        }
    }

    public int getAccessCount() {
        return accessCount;
    }

    public long getMemoryUsage() {
        return realImage != null ? realImage.getMemoryUsage() : 0;
    }
}
package com.designpatterns.structural.proxy;

/**
 * RealSubject class that represents an actual image
 * This is the expensive object we want to proxy
 */
public class RealImage implements Image {
    private final String filename;
    private final int width;
    private final int height;
    private byte[] imageData; // Simulated image data
    private boolean isLoaded;

    public RealImage(String filename, int width, int height) {
        this.filename = filename;
        this.width = width;
        this.height = height;
        this.isLoaded = false;
    }

    private void loadImageFromDisk() {
        if (!isLoaded) {
            // Simulate loading a large image from disk
            try {
                System.out.println("Loading image: " + filename);
                Thread.sleep(1000); // Simulate loading time
                this.imageData = new byte[width * height * 3]; // RGB data
                this.isLoaded = true;
                System.out.println("Image loaded: " + filename);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Image loading interrupted: " + filename);
            }
        }
    }

    @Override
    public void display() {
        loadImageFromDisk();
        if (isLoaded) {
            System.out.println("Displaying image: " + filename +
                    " (" + width + "x" + height + ")");
        }
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
        return isLoaded;
    }

    // Method to simulate memory usage
    public long getMemoryUsage() {
        return isLoaded ? (long) width * height * 3 : 0;
    }
}
package com.designpatterns.structural.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Client class that demonstrates the use of Image proxies
 */
public class ImageGallery {
    private final Map<String, ImageProxy> images;
    private final long memoryLimit;
    private long currentMemoryUsage;

    public ImageGallery(long memoryLimitInBytes) {
        this.images = new HashMap<>();
        this.memoryLimit = memoryLimitInBytes;
        this.currentMemoryUsage = 0;
    }

    public void addImage(String filename, int width, int height) {
        images.put(filename, new ImageProxy(filename, width, height));
    }

    public void displayImage(String filename) {
        ImageProxy image = images.get(filename);
        if (image == null) {
            System.out.println("Image not found: " + filename);
            return;
        }

        // Check if loading this image would exceed memory limit
        long potentialMemoryUsage = image.isLoaded() ? currentMemoryUsage
                : currentMemoryUsage + ((long) image.getWidth() * image.getHeight() * 3);

        if (potentialMemoryUsage > memoryLimit) {
            // Unload least accessed images until we have enough memory
            unloadLeastAccessedImages(potentialMemoryUsage - memoryLimit);
        }

        // Display the image and update memory usage
        image.display();
        updateMemoryUsage();
    }

    private void unloadLeastAccessedImages(final long memoryNeededToFree) {
        final long[] remainingMemoryToFree = { memoryNeededToFree };
        images.values().stream()
                .filter(Image::isLoaded)
                .sorted((a, b) -> Integer.compare(a.getAccessCount(), b.getAccessCount()))
                .takeWhile(image -> remainingMemoryToFree[0] > 0)
                .forEach(image -> {
                    remainingMemoryToFree[0] -= image.getMemoryUsage();
                    image.unloadImage();
                });
    }

    private void updateMemoryUsage() {
        currentMemoryUsage = images.values().stream()
                .mapToLong(ImageProxy::getMemoryUsage)
                .sum();
    }

    public long getCurrentMemoryUsage() {
        return currentMemoryUsage;
    }

    public int getLoadedImagesCount() {
        return (int) images.values().stream()
                .filter(Image::isLoaded)
                .count();
    }

    public void unloadAllImages() {
        images.values().forEach(ImageProxy::unloadImage);
        currentMemoryUsage = 0;
    }
}
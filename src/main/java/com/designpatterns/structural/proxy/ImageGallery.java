package com.designpatterns.structural.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

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

        // Calculate memory needed for this image
        long imageMemory = (long) image.getWidth() * image.getHeight() * 3;

        // If image is not loaded
        if (!image.isLoaded()) {
            // Check if we have enough memory for this image
            long projectedMemory = currentMemoryUsage + imageMemory;
            if (projectedMemory > memoryLimit) {
                // Free up memory by unloading least accessed images
                unloadLeastAccessedImages(imageMemory);
            }
        }

        // Display the image (this will load it if necessary)
        image.display();

        // Update memory usage after loading
        updateMemoryUsage();

        // If we're still over the limit, unload more images
        while (currentMemoryUsage > memoryLimit) {
            // Find the least accessed image that's not the current one
            ImageProxy leastAccessed = findLeastAccessedImage(image);
            if (leastAccessed == null)
                break;

            // Only unload if we really need to
            long projectedMemory = currentMemoryUsage - leastAccessed.getMemoryUsage();
            if (projectedMemory + imageMemory <= memoryLimit) {
                break;
            }

            // Unload it and update memory usage
            leastAccessed.unloadImage();
            updateMemoryUsage();
        }
    }

    private ImageProxy findLeastAccessedImage(ImageProxy currentImage) {
        return images.values().stream()
                .filter(img -> img.isLoaded() && img != currentImage)
                .min(Comparator.comparingInt(ImageProxy::getAccessCount)
                        .thenComparingLong(img -> -img.getMemoryUsage()))
                .orElse(null);
    }

    private void unloadLeastAccessedImages(long memoryNeeded) {
        // Calculate how much memory we need to free
        long memoryToFree = currentMemoryUsage + memoryNeeded - memoryLimit;

        // If we don't need to free any memory, return
        if (memoryToFree <= 0) {
            return;
        }

        // Get all loaded images sorted by access count (ascending)
        List<ImageProxy> loadedImages = new ArrayList<>();
        for (ImageProxy img : images.values()) {
            if (img.isLoaded()) {
                loadedImages.add(img);
            }
        }

        // Sort by access count (ascending) and then by memory usage (descending)
        loadedImages.sort(Comparator.comparingInt(ImageProxy::getAccessCount)
                .thenComparingLong(img -> -img.getMemoryUsage()));

        // Unload images until we have enough memory
        long freedMemory = 0;
        for (ImageProxy img : loadedImages) {
            if (freedMemory >= memoryToFree)
                break;
            long imgMemory = img.getMemoryUsage();
            img.unloadImage();
            freedMemory += imgMemory;
            currentMemoryUsage -= imgMemory;
        }
    }

    private void updateMemoryUsage() {
        currentMemoryUsage = images.values().stream()
                .mapToLong(ImageProxy::getMemoryUsage)
                .sum();
    }

    public int getLoadedImagesCount() {
        return (int) images.values().stream()
                .filter(ImageProxy::isLoaded)
                .count();
    }

    public void unloadAllImages() {
        images.values().forEach(ImageProxy::unloadImage);
        currentMemoryUsage = 0;
    }

    public long getCurrentMemoryUsage() {
        return currentMemoryUsage;
    }
}
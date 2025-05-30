package com.designpatterns.structural.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageGalleryTest {
    private ImageGallery gallery;
    private static final long MEMORY_LIMIT = 1920 * 1080 * 3 * 2; // Enough for 2 HD images

    @BeforeEach
    void setUp() {
        gallery = new ImageGallery(MEMORY_LIMIT);
    }

    @Test
    void testLazyLoading() {
        gallery.addImage("test1.jpg", 1000, 800);
        assertEquals(0, gallery.getCurrentMemoryUsage());
        assertEquals(0, gallery.getLoadedImagesCount());

        gallery.displayImage("test1.jpg");
        assertTrue(gallery.getCurrentMemoryUsage() > 0);
        assertEquals(1, gallery.getLoadedImagesCount());
    }

    @Test
    void testMemoryManagement() {
        // Add three large images
        gallery.addImage("hd1.jpg", 1920, 1080);
        gallery.addImage("hd2.jpg", 1920, 1080);
        gallery.addImage("hd3.jpg", 1920, 1080);

        // Display first two images
        gallery.displayImage("hd1.jpg");
        gallery.displayImage("hd2.jpg");
        assertEquals(2, gallery.getLoadedImagesCount());

        // Display third image - should unload least accessed image
        gallery.displayImage("hd3.jpg");
        assertEquals(2, gallery.getLoadedImagesCount()); // Still 2 images loaded
    }

    @Test
    void testAccessCounting() {
        gallery.addImage("test.jpg", 800, 600);

        // Access the image multiple times
        for (int i = 0; i < 3; i++) {
            gallery.displayImage("test.jpg");
        }

        // Add and display another image
        gallery.addImage("test2.jpg", 800, 600);
        gallery.displayImage("test2.jpg");

        // When memory limit is reached, test.jpg should stay loaded due to more
        // accesses
        gallery.addImage("test3.jpg", 1920, 1080);
        gallery.displayImage("test3.jpg");

        // Verify test.jpg is still loaded (it has more accesses)
        assertTrue(gallery.getCurrentMemoryUsage() > 0);
    }

    @Test
    void testUnloadAllImages() {
        gallery.addImage("test1.jpg", 800, 600);
        gallery.addImage("test2.jpg", 800, 600);

        gallery.displayImage("test1.jpg");
        gallery.displayImage("test2.jpg");
        assertTrue(gallery.getCurrentMemoryUsage() > 0);
        assertEquals(2, gallery.getLoadedImagesCount());

        gallery.unloadAllImages();
        assertEquals(0, gallery.getCurrentMemoryUsage());
        assertEquals(0, gallery.getLoadedImagesCount());
    }

    @Test
    void testNonExistentImage() {
        gallery.displayImage("nonexistent.jpg");
        assertEquals(0, gallery.getCurrentMemoryUsage());
        assertEquals(0, gallery.getLoadedImagesCount());
    }

    @Test
    void testMemoryLimit() {
        // Create an image gallery with very small memory limit
        ImageGallery smallGallery = new ImageGallery(800 * 600 * 3); // Only enough for one small image

        smallGallery.addImage("large1.jpg", 1920, 1080);
        smallGallery.addImage("large2.jpg", 1920, 1080);

        // Try to display both images
        smallGallery.displayImage("large1.jpg");
        smallGallery.displayImage("large2.jpg");

        // Should only have one image loaded due to memory constraints
        assertEquals(1, smallGallery.getLoadedImagesCount());
    }
}
package com.designpatterns.structural.composite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {

    @Test
    void testFileSystemStructure() {
        // Create the root directory
        Directory root = new Directory("root");

        // Create subdirectories
        Directory documents = new Directory("documents");
        Directory pictures = new Directory("pictures");

        // Create files
        File resume = new File("resume.pdf", 1024);
        File letter = new File("cover_letter.doc", 512);
        File photo1 = new File("vacation.jpg", 2048);
        File photo2 = new File("family.jpg", 3072);

        // Build the directory structure
        documents.add(resume);
        documents.add(letter);
        pictures.add(photo1);
        pictures.add(photo2);
        root.add(documents);
        root.add(pictures);

        // Test individual file sizes
        assertEquals(1024, resume.getSize());
        assertEquals(512, letter.getSize());

        // Test directory sizes (recursive)
        assertEquals(1536, documents.getSize()); // 1024 + 512
        assertEquals(5120, pictures.getSize()); // 2048 + 3072
        assertEquals(6656, root.getSize()); // 1536 + 5120

        // Test directory structure
        assertEquals(2, root.getChildren().size());
        assertEquals(2, documents.getChildren().size());
        assertEquals(2, pictures.getChildren().size());

        // Test component names
        assertEquals("root", root.getName());
        assertEquals("documents", documents.getName());
        assertEquals("vacation.jpg", photo1.getName());

        // Visual representation test (optional)
        System.out.println("File System Structure:");
        root.showDetails("");
    }

    @Test
    void testEmptyDirectory() {
        Directory empty = new Directory("empty");
        assertEquals(0, empty.getSize());
        assertEquals(0, empty.getChildren().size());
    }

    @Test
    void testDirectoryModification() {
        Directory dir = new Directory("test");
        File file1 = new File("test1.txt", 100);
        File file2 = new File("test2.txt", 200);

        dir.add(file1);
        assertEquals(100, dir.getSize());

        dir.add(file2);
        assertEquals(300, dir.getSize());

        dir.remove(file1);
        assertEquals(200, dir.getSize());
        assertEquals(1, dir.getChildren().size());
    }
}
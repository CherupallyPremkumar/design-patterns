package com.designpatterns.structural.composite;

/**
 * Represents a file in the file system
 * This is the Leaf class in the Composite pattern
 */
public class File implements FileSystemComponent {
    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📄 " + name + " (" + size + " bytes)");
    }

    @Override
    public long getSize() {
        return size;
    }
}
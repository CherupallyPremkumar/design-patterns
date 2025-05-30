package com.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system
 * This is the Composite class in the Composite pattern
 */
public class Directory implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children;

    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    public List<FileSystemComponent> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📁 " + name + " (" + getSize() + " bytes)");
        for (FileSystemComponent component : children) {
            component.showDetails(indent + "  ");
        }
    }

    @Override
    public long getSize() {
        return children.stream()
                .mapToLong(FileSystemComponent::getSize)
                .sum();
    }
}
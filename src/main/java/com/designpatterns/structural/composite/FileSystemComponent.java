package com.designpatterns.structural.composite;

/**
 * Component interface representing both files and directories
 * This is the base Component in the Composite pattern
 */
public interface FileSystemComponent {
    String getName();

    void showDetails(String indent);

    long getSize();
}
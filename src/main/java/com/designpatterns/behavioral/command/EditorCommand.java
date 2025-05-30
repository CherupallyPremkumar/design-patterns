package com.designpatterns.behavioral.command;

/**
 * The Command interface that all concrete commands must implement
 */
public interface EditorCommand {
    void execute();

    void undo();

    String getDescription();
}
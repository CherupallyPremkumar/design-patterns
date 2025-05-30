package com.designpatterns.behavioral.command;

/**
 * Command interface defining the contract for all commands.
 * This is the Command interface in the Command pattern.
 */
public interface Command {
    void execute();

    void undo();

    String getDescription();
}
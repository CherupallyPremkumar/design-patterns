package com.designpatterns.behavioral.command;

import java.util.Stack;

/**
 * SmartHomeController class acting as the invoker in the Command pattern.
 * Manages command execution and maintains history for undo operations.
 */
public class SmartHomeController {
    private final Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
        } else {
            System.out.println("No commands to undo");
        }
    }

    public void undoAllCommands() {
        while (!history.isEmpty()) {
            undoLastCommand();
        }
    }

    public int getHistorySize() {
        return history.size();
    }

    public String getLastCommandDescription() {
        if (!history.isEmpty()) {
            return history.peek().getDescription();
        }
        return "No commands in history";
    }
}
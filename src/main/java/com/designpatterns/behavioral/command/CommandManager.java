package com.designpatterns.behavioral.command;

import java.util.Stack;

/**
 * The Invoker class that manages command execution and history
 */
public class CommandManager {
    private final Stack<EditorCommand> undoStack;
    private final Stack<EditorCommand> redoStack;

    public CommandManager() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void executeCommand(EditorCommand command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // Clear redo stack when new command is executed
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    public void undo() {
        if (canUndo()) {
            EditorCommand command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (canRedo()) {
            EditorCommand command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    public String getUndoHistory() {
        StringBuilder history = new StringBuilder("Undo Stack:\n");
        for (EditorCommand command : undoStack) {
            history.append("- ").append(command.getDescription()).append("\n");
        }
        return history.toString();
    }
}
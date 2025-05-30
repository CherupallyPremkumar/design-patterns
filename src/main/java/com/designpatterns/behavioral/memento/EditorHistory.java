package com.designpatterns.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Caretaker class that manages the history of editor states
 */
public class EditorHistory {
    private final Deque<EditorMemento> undoStack;
    private final Deque<EditorMemento> redoStack;
    private final TextEditor editor;
    private static final int MAX_HISTORY = 100;

    public EditorHistory(TextEditor editor) {
        this.editor = editor;
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
        // Save initial empty state
        save();
    }

    public void save() {
        EditorMemento memento = editor.save();
        undoStack.push(memento);
        redoStack.clear(); // Clear redo stack when new change is made
        if (undoStack.size() > MAX_HISTORY + 1) { // +1 for initial state
            undoStack.removeLast();
        }
    }

    public boolean undo() {
        if (undoStack.size() <= 1) { // Keep at least one state (initial state)
            return false;
        }

        EditorMemento currentState = editor.save();
        redoStack.push(currentState);
        undoStack.pop(); // Remove current state
        editor.restore(undoStack.peek()); // Restore to previous state
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }

        EditorMemento redoState = redoStack.pop();
        undoStack.push(editor.save());
        editor.restore(redoState);
        return true;
    }

    public int getUndoCount() {
        return Math.max(0, undoStack.size() - 1); // Don't count initial state
    }

    public int getRedoCount() {
        return redoStack.size();
    }

    public void clear() {
        undoStack.clear();
        redoStack.clear();
        save(); // Save initial empty state
    }
}
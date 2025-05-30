package com.designpatterns.behavioral.memento;

/**
 * Memento class that stores the state of the text editor
 */
public class EditorMemento {
    private final String content;
    private final int cursorPosition;
    private final String selectedText;

    public EditorMemento(String content, int cursorPosition, String selectedText) {
        this.content = content;
        this.cursorPosition = cursorPosition;
        this.selectedText = selectedText;
    }

    // Package-private getters to restrict access to the Originator
    String getContent() {
        return content;
    }

    int getCursorPosition() {
        return cursorPosition;
    }

    String getSelectedText() {
        return selectedText;
    }
}
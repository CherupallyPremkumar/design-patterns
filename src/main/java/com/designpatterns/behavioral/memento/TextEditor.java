package com.designpatterns.behavioral.memento;

/**
 * Originator class that creates and restores from mementos
 */
public class TextEditor {
    private StringBuilder content;
    private int cursorPosition;
    private String selectedText;

    public TextEditor() {
        this.content = new StringBuilder();
        this.cursorPosition = 0;
        this.selectedText = "";
    }

    public void write(String text) {
        if (selectedText.length() > 0) {
            // Replace selected text
            int start = cursorPosition - selectedText.length();
            content.replace(start, cursorPosition, text);
            cursorPosition = start + text.length();
        } else {
            // Insert at cursor position
            content.insert(cursorPosition, text);
            cursorPosition += text.length();
        }
        selectedText = "";
    }

    public void selectText(int start, int end) {
        if (start >= 0 && end <= content.length() && start <= end) {
            selectedText = content.substring(start, end);
            cursorPosition = end;
        }
    }

    public void delete() {
        if (selectedText.length() > 0) {
            // Delete selected text
            int start = cursorPosition - selectedText.length();
            content.delete(start, cursorPosition);
            cursorPosition = start;
            selectedText = "";
        } else if (cursorPosition > 0) {
            // Delete character before cursor
            content.deleteCharAt(--cursorPosition);
        }
    }

    public void setCursorPosition(int position) {
        if (position >= 0 && position <= content.length()) {
            this.cursorPosition = position;
            this.selectedText = "";
        }
    }

    // Create a memento
    public EditorMemento save() {
        return new EditorMemento(content.toString(), cursorPosition, selectedText);
    }

    // Restore from a memento
    public void restore(EditorMemento memento) {
        this.content = new StringBuilder(memento.getContent());
        this.cursorPosition = memento.getCursorPosition();
        this.selectedText = memento.getSelectedText();
    }

    // Getters for testing and display
    public String getContent() {
        return content.toString();
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public String getSelectedText() {
        return selectedText;
    }
}
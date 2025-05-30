package com.designpatterns.behavioral.command;

/**
 * The Receiver class that performs the actual text editing operations
 */
public class TextEditor {
    private StringBuilder content;
    private int cursorPosition;

    public TextEditor() {
        this.content = new StringBuilder();
        this.cursorPosition = 0;
    }

    public void insertText(String text) {
        content.insert(cursorPosition, text);
        cursorPosition += text.length();
    }

    public void deleteText(int length) {
        if (cursorPosition >= length) {
            content.delete(cursorPosition - length, cursorPosition);
            cursorPosition -= length;
        }
    }

    public void moveCursor(int position) {
        if (position >= 0 && position <= content.length()) {
            cursorPosition = position;
        }
    }

    public String getContent() {
        return content.toString();
    }

    public int getCursorPosition() {
        return cursorPosition;
    }
}
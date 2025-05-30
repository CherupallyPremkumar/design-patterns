package com.designpatterns.behavioral.command;

/**
 * Command for inserting text at the current cursor position
 */
public class InsertTextCommand implements EditorCommand {
    private final TextEditor editor;
    private final String text;
    private final int originalPosition;

    public InsertTextCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.text = text;
        this.originalPosition = editor.getCursorPosition();
    }

    @Override
    public void execute() {
        editor.insertText(text);
    }

    @Override
    public void undo() {
        editor.moveCursor(originalPosition + text.length());
        editor.deleteText(text.length());
    }

    @Override
    public String getDescription() {
        return "Insert '" + text + "'";
    }
}
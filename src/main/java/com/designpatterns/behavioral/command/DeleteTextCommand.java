package com.designpatterns.behavioral.command;

/**
 * Command for deleting text at the current cursor position
 */
public class DeleteTextCommand implements EditorCommand {
    private final TextEditor editor;
    private final int length;
    private String deletedText;
    private final int originalPosition;

    public DeleteTextCommand(TextEditor editor, int length) {
        this.editor = editor;
        this.length = length;
        this.originalPosition = editor.getCursorPosition();
        // Store the text that will be deleted for undo
        int startPos = Math.max(0, originalPosition - length);
        this.deletedText = editor.getContent().substring(startPos, originalPosition);
    }

    @Override
    public void execute() {
        editor.deleteText(length);
    }

    @Override
    public void undo() {
        editor.moveCursor(originalPosition - length);
        editor.insertText(deletedText);
        editor.moveCursor(originalPosition);
    }

    @Override
    public String getDescription() {
        return "Delete " + length + " characters";
    }
}
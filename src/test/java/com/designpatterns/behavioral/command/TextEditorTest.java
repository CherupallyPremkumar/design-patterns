package com.designpatterns.behavioral.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextEditorTest {
    private TextEditor editor;
    private CommandManager commandManager;

    @BeforeEach
    void setUp() {
        editor = new TextEditor();
        commandManager = new CommandManager();
    }

    @Test
    void testBasicTextInsertion() {
        EditorCommand insertCommand = new InsertTextCommand(editor, "Hello");
        commandManager.executeCommand(insertCommand);
        assertEquals("Hello", editor.getContent());
        assertEquals(5, editor.getCursorPosition());
    }

    @Test
    void testTextDeletion() {
        commandManager.executeCommand(new InsertTextCommand(editor, "Hello World"));
        commandManager.executeCommand(new DeleteTextCommand(editor, 6)); // Delete "World"
        assertEquals("Hello", editor.getContent());
        assertEquals(5, editor.getCursorPosition());
    }

    @Test
    void testUndoRedo() {
        // Insert text
        commandManager.executeCommand(new InsertTextCommand(editor, "Hello"));
        assertEquals("Hello", editor.getContent());

        // Undo insertion
        commandManager.undo();
        assertEquals("", editor.getContent());

        // Redo insertion
        commandManager.redo();
        assertEquals("Hello", editor.getContent());
    }

    @Test
    void testComplexEditing() {
        // Insert "Hello"
        commandManager.executeCommand(new InsertTextCommand(editor, "Hello"));

        // Insert " World"
        commandManager.executeCommand(new InsertTextCommand(editor, " World"));
        assertEquals("Hello World", editor.getContent());

        // Delete "World"
        commandManager.executeCommand(new DeleteTextCommand(editor, 5));
        assertEquals("Hello ", editor.getContent());

        // Undo twice
        commandManager.undo(); // Undo delete
        commandManager.undo(); // Undo second insert
        assertEquals("Hello", editor.getContent());

        // Redo everything
        commandManager.redo(); // Redo second insert
        commandManager.redo(); // Redo delete
        assertEquals("Hello ", editor.getContent());
    }

    @Test
    void testCommandHistory() {
        commandManager.executeCommand(new InsertTextCommand(editor, "Hello"));
        commandManager.executeCommand(new InsertTextCommand(editor, " World"));
        commandManager.executeCommand(new DeleteTextCommand(editor, 5));

        String history = commandManager.getUndoHistory();
        assertTrue(history.contains("Insert 'Hello'"));
        assertTrue(history.contains("Insert ' World'"));
        assertTrue(history.contains("Delete 5 characters"));
    }

    @Test
    void testUndoRedoAvailability() {
        assertFalse(commandManager.canUndo());
        assertFalse(commandManager.canRedo());

        commandManager.executeCommand(new InsertTextCommand(editor, "Test"));
        assertTrue(commandManager.canUndo());
        assertFalse(commandManager.canRedo());

        commandManager.undo();
        assertFalse(commandManager.canUndo());
        assertTrue(commandManager.canRedo());

        commandManager.redo();
        assertTrue(commandManager.canUndo());
        assertFalse(commandManager.canRedo());
    }
}
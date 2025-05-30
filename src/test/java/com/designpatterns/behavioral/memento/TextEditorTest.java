package com.designpatterns.behavioral.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextEditorTest {
    private TextEditor editor;
    private EditorHistory history;

    @BeforeEach
    void setUp() {
        editor = new TextEditor();
        history = new EditorHistory(editor);
    }

    @Test
    void testBasicEditing() {
        editor.write("Hello");
        assertEquals("Hello", editor.getContent());
        assertEquals(5, editor.getCursorPosition());

        editor.write(" World");
        assertEquals("Hello World", editor.getContent());
        assertEquals(11, editor.getCursorPosition());
    }

    @Test
    void testTextSelection() {
        editor.write("Hello World");
        editor.selectText(0, 5); // Select "Hello"
        assertEquals("Hello", editor.getSelectedText());
        assertEquals(5, editor.getCursorPosition());

        editor.write("Hi"); // Replace "Hello" with "Hi"
        assertEquals("Hi World", editor.getContent());
        assertEquals(2, editor.getCursorPosition());
    }

    @Test
    void testDeletion() {
        editor.write("Hello World");
        editor.selectText(6, 11); // Select "World"
        editor.delete(); // Delete selected text
        assertEquals("Hello ", editor.getContent());
        assertEquals(6, editor.getCursorPosition());

        editor.delete(); // Delete space
        assertEquals("Hello", editor.getContent());
        assertEquals(5, editor.getCursorPosition());
    }

    @Test
    void testUndoRedo() {
        // Initial text
        editor.write("Hello");
        history.save();
        assertEquals("Hello", editor.getContent());

        // Add more text
        editor.write(" World");
        history.save();
        assertEquals("Hello World", editor.getContent());

        // Undo
        assertTrue(history.undo());
        assertEquals("Hello", editor.getContent());

        // Redo
        assertTrue(history.redo());
        assertEquals("Hello World", editor.getContent());
    }

    @Test
    void testMultipleUndoRedo() {
        editor.write("First");
        history.save();
        editor.write(" Second");
        history.save();
        editor.write(" Third");
        history.save();

        // Undo twice
        assertTrue(history.undo());
        assertEquals("First Second", editor.getContent());
        assertTrue(history.undo());
        assertEquals("First", editor.getContent());

        // Redo everything
        assertTrue(history.redo());
        assertEquals("First Second", editor.getContent());
        assertTrue(history.redo());
        assertEquals("First Second Third", editor.getContent());
    }

    @Test
    void testUndoLimit() {
        // Add more states than the maximum history size
        for (int i = 0; i < 105; i++) {
            editor.write(String.valueOf(i));
            history.save();
        }

        // Verify that we can't undo more than MAX_HISTORY times
        int undoCount = 0;
        while (history.undo()) {
            undoCount++;
        }
        assertEquals(100, undoCount); // MAX_HISTORY constant from EditorHistory
    }

    @Test
    void testRedoStackClearedOnNewEdit() {
        editor.write("First");
        history.save();
        editor.write(" Second");
        history.save();
        editor.write(" Third");
        history.save();

        // Undo twice and make a new edit
        history.undo();
        history.undo();
        assertEquals("First", editor.getContent());

        editor.write(" New");
        history.save();
        assertEquals("First New", editor.getContent());

        // Try to redo - should not be possible
        assertFalse(history.redo());
        assertEquals("First New", editor.getContent());
    }

    @Test
    void testEmptyHistoryOperations() {
        assertFalse(history.undo()); // Cannot undo with no history
        assertFalse(history.redo()); // Cannot redo with no history
        assertEquals("", editor.getContent());
        assertEquals(0, editor.getCursorPosition());
    }
}
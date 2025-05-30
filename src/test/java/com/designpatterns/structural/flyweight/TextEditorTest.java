package com.designpatterns.structural.flyweight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;

public class TextEditorTest {
    private TextEditor editor;

    @BeforeEach
    void setUp() {
        editor = new TextEditor();
        CharacterFormatFactory.clearCache();
    }

    @Test
    void testFormatReuse() {
        // Add multiple characters with the same format
        Color blue = new Color(0, 0, 255);
        for (int i = 0; i < 10; i++) {
            editor.addCharacter('A', i, "Arial", 12, blue, true, false);
        }

        // Despite having 10 characters, should only have 1 format object
        assertEquals(10, editor.getCharacterCount());
        assertEquals(1, editor.getFormatCount());
    }

    @Test
    void testMultipleFormats() {
        // Add characters with different formats
        editor.addCharacter('H', 0, "Arial", 12, Color.BLACK, false, false);
        editor.addCharacter('e', 1, "Arial", 12, Color.BLACK, false, false);
        editor.addCharacter('l', 2, "Arial", 12, Color.RED, true, false);
        editor.addCharacter('l', 3, "Arial", 12, Color.RED, true, false);
        editor.addCharacter('o', 4, "Arial", 14, Color.BLUE, false, true);

        // Should have 3 different formats (black normal, red bold, blue italic)
        assertEquals(5, editor.getCharacterCount());
        assertEquals(3, editor.getFormatCount());
    }

    @Test
    void testClearEditor() {
        editor.addCharacter('A', 0, "Arial", 12, Color.BLACK, false, false);
        editor.addCharacter('B', 1, "Arial", 14, Color.RED, true, false);

        editor.clear();
        assertEquals(0, editor.getCharacterCount());
        assertEquals(0, editor.getFormatCount());
    }

    @Test
    void testMemoryEfficiency() {
        // Create 1000 characters with only 2 different formats
        Color color1 = new Color(100, 100, 100);
        Color color2 = new Color(200, 200, 200);

        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                editor.addCharacter('X', i, "Arial", 12, color1, false, false);
            } else {
                editor.addCharacter('Y', i, "Times", 14, color2, true, true);
            }
        }

        // Should have 1000 characters but only 2 format objects
        assertEquals(1000, editor.getCharacterCount());
        assertEquals(2, editor.getFormatCount());
    }

    @Test
    void testDisplayOrder() {
        // Add characters in random order
        editor.addCharacter('o', 4, "Arial", 12, Color.BLACK, false, false);
        editor.addCharacter('H', 0, "Arial", 12, Color.BLACK, false, false);
        editor.addCharacter('l', 2, "Arial", 12, Color.BLACK, false, false);
        editor.addCharacter('l', 3, "Arial", 12, Color.BLACK, false, false);
        editor.addCharacter('e', 1, "Arial", 12, Color.BLACK, false, false);

        // Display should show them in correct order
        editor.displayText();
        // Visual verification of output order: "Hello"
    }
}
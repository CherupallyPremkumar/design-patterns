package com.designpatterns.structural.flyweight;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Client class that uses the Flyweight pattern to manage text formatting
 */
public class TextEditor {
    private final List<FormattedCharacter> characters;

    public TextEditor() {
        this.characters = new ArrayList<>();
    }

    public void addCharacter(char c, int position, String fontFamily, int fontSize,
            Color color, boolean isBold, boolean isItalic) {
        CharacterFormat format = CharacterFormatFactory.getFormat(
                fontFamily, fontSize, color, isBold, isItalic);
        characters.add(new FormattedCharacter(c, position, format));
    }

    public void displayText() {
        characters.stream()
                .sorted((c1, c2) -> Integer.compare(c1.getPosition(), c2.getPosition()))
                .forEach(FormattedCharacter::render);
    }

    public int getCharacterCount() {
        return characters.size();
    }

    public int getFormatCount() {
        return CharacterFormatFactory.getCacheSize();
    }

    public void clear() {
        characters.clear();
        CharacterFormatFactory.clearCache();
    }
}
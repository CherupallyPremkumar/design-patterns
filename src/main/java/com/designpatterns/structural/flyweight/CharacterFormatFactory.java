package com.designpatterns.structural.flyweight;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory that creates and manages flyweight objects
 */
public class CharacterFormatFactory {
    private static final Map<String, CharacterFormat> formatCache = new HashMap<>();

    public static CharacterFormat getFormat(String fontFamily, int fontSize, Color color, boolean isBold,
            boolean isItalic) {
        // Create a key for the format combination
        String key = String.format("%s_%d_%d_%d_%d_%b_%b",
                fontFamily, fontSize, color.getRed(), color.getGreen(), color.getBlue(), isBold, isItalic);

        // Return existing format if it exists, otherwise create new one
        return formatCache.computeIfAbsent(key,
                k -> new CharacterFormat(fontFamily, fontSize, color, isBold, isItalic));
    }

    public static int getCacheSize() {
        return formatCache.size();
    }

    public static void clearCache() {
        formatCache.clear();
    }
}
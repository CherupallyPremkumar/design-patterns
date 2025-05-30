package com.designpatterns.structural.flyweight;

import java.awt.Color;

/**
 * Flyweight class containing intrinsic state shared by multiple characters
 */
public class CharacterFormat {
    private final String fontFamily;
    private final int fontSize;
    private final Color color;
    private final boolean isBold;
    private final boolean isItalic;

    public CharacterFormat(String fontFamily, int fontSize, Color color, boolean isBold, boolean isItalic) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.color = color;
        this.isBold = isBold;
        this.isItalic = isItalic;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Color getColor() {
        return color;
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    @Override
    public String toString() {
        return String.format("Format[font=%s, size=%d, color=rgb(%d,%d,%d), bold=%b, italic=%b]",
                fontFamily, fontSize, color.getRed(), color.getGreen(), color.getBlue(), isBold, isItalic);
    }
}
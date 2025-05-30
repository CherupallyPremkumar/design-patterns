package com.designpatterns.structural.flyweight;

/**
 * Context class that uses the flyweight object
 * Contains both intrinsic state (shared formatting) and extrinsic state
 * (character and position)
 */
public class FormattedCharacter {
    private final char character; // Extrinsic state
    private final int position; // Extrinsic state
    private final CharacterFormat format; // Intrinsic state (flyweight)

    public FormattedCharacter(char character, int position, CharacterFormat format) {
        this.character = character;
        this.position = position;
        this.format = format;
    }

    public char getCharacter() {
        return character;
    }

    public int getPosition() {
        return position;
    }

    public CharacterFormat getFormat() {
        return format;
    }

    public void render() {
        System.out.printf("Character '%c' at position %d with %s%n",
                character, position, format.toString());
    }
}
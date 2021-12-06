package com.github.hc747.poker;

public enum Suit {
    CLUB('C'),
    HEART('H'),
    SPADE('S'),
    DIAMOND('D')
    ;

    private final char character;

    Suit(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return Utilities.format(this);
    }

    public static Suit parse(char character) {
        for (Suit suit : Suit.values()) {
            if (suit.character == character) {
                return suit;
            }
        }
        return null;
    }

    public static Suit parseJava8(char character) {
        return Utilities.find(Suit.values(), suit -> suit.character == character);
    }
}

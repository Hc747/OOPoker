package com.github.hc747.poker;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.stream.Stream;

public enum Suit {
    CLUB('C'),
    HEART('H'),
    SPADE('S'),
    DIAMOND('D')
    ;

    private static final Collection<Suit> ALL = Collections.unmodifiableSet(EnumSet.allOf(Suit.class));

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

    public static Collection<Suit> elements() {
        return ALL;
    }

    public static Stream<Suit> stream() {
        return elements().stream();
    }
}

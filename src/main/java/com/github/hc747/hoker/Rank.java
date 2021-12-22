package com.github.hc747.hoker;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.stream.Stream;

public enum Rank {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A')
    ;

    private static final Collection<Rank> ALL = Collections.unmodifiableSet(EnumSet.allOf(Rank.class));

    private final char character;

    Rank(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return Utilities.format(this);
    }

    public static Rank parse(char character) {
        for (Rank rank : Rank.values()) {
            if (rank.character == character) {
                return rank;
            }
        }
        return null;
    }

    public static Rank parseJava8(char character) {
        return Utilities.find(Rank.values(), rank -> rank.character == character);
    }

    public static Collection<Rank> elements() {
        return ALL;
    }

    public static Stream<Rank> stream() {
        return elements().stream();
    }
}

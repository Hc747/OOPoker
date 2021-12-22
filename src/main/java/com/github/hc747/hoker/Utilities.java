package com.github.hc747.hoker;

import java.util.Arrays;
import java.util.function.Predicate;

public final class Utilities {

    public static <E extends Enum<E>> E find(E[] values, Predicate<E> condition) {
        return Arrays.stream(values).filter(condition).findAny().orElse(null);
    }

    public static <E extends Enum<E>> String format(E value) {
        final String name = value.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private Utilities() {
        // prevent instantiation
    }
}

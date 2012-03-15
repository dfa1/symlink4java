package com.humaorie.link;

public class dbc {

    public static void precondition(boolean precondition, String format, Object... args) {
        if (!precondition) {
            throw new IllegalArgumentException(String.format(format, args));
        }
    }
}

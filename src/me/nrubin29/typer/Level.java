package me.nrubin29.typer;

import java.util.Random;

public enum Level {

    ONE("One - f, j", 20, 'f', 'j'),
    TWO("Two - g, h", 20, 'g', 'h'),
    THREE("Three - f, g, h, j", 20, 'f', 'g', 'h', 'j');

    private static final Random RAND = new Random();

    private final String name;
    private final int length;
    private final char[] chars;

    Level(String name, int length, char... chars) {
        this.name = name;
        this.length = length;
        this.chars = chars;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    char[] getChars() {
        return chars;
    }

    public String getRandomString() {
        StringBuilder str = new StringBuilder();

        boolean lastWasSpace = false;

        for (int i = 0; i < getLength(); i++) {
            int j = RAND.nextInt(getChars().length + 1);
            char c;

            if (j == getChars().length) {
                if (!lastWasSpace) {
                    c = ' ';
                    lastWasSpace = true;
                } else {
                    c = getChars()[j - 1];
                    lastWasSpace = false;
                }
            } else {
                c = getChars()[j];
                lastWasSpace = false;
            }

            str.append(c);
        }

        return str.toString().trim();
    }
}
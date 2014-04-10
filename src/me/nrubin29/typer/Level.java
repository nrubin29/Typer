package me.nrubin29.typer;

public enum Level {

    ONE("One - f, j", 20, 'f', 'j'),
    TWO("Two - g, h", 20, 'g', 'h'),
    THREE("Three - f, g, h, j", 20, 'f', 'g', 'h', 'j');

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

        int numChars = Utils.getRandomNumber(getLength() - 2, getLength() + 2);
        int numSpaces = Utils.getRandomNumber(2, 6);

        for (int i = 0; i < numChars; i++) {
            int j = Utils.getRandomNumber(0, getChars().length + 1);
            char c;

            if (j == getChars().length) {
                c = getChars()[j - 1];
            } else {
                c = getChars()[j];
            }

            str.append(c);
        }

        for (int i = 0; i < numSpaces; i++) {
            int j;

            do {
                j = Utils.getRandomNumber(0, str.length());
            }
            while (str.charAt(Utils.fixNumber(j - 1, 0, str.length() - 1)) == ' ' || str.charAt(Utils.fixNumber(j + 1, 0, str.length() - 1)) == ' ');

            str.insert(j, ' ');
        }

        return str.toString().trim();
    }
}
package me.nrubin29.typer;

import java.awt.*;
import java.util.Random;

class Utils {

    public static final Font LARGE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 54);
    public static final Font SMALL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    private static final Random RAND = new Random();

    public static int getRandomNumber(int floor, int ceil) {
        int rand = RAND.nextInt(ceil);
        if (rand < floor) return getRandomNumber(floor, ceil);
        else return rand;
    }

    public static int fixNumber(int num, int min, int max) {
        if (num < min) num = min;
        if (num > max) num = max;
        return num;
    }

    public static double fixNumber(double num, double min, double max) {
        if (num < min) num = min;
        if (num > max) num = max;
        return num;
    }
}
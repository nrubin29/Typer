package me.nrubin29.typer;

import java.util.Random;

class Utils {

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
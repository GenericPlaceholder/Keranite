package net.generic404_.keranite.util;

import java.util.Random;

public class RandomUtil {
    public static int getRandom(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}

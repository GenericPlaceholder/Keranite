package net.generic404_.keranite.util;

import java.util.Random;

public class RandomUtil {
    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    public static float getRandomFloat(float min,float max){
        Random rand = new Random();
        return rand.nextFloat((max-min)+1)+min;
    }
}

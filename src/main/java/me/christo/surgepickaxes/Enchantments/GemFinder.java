package me.christo.surgepickaxes.Enchantments;

import java.util.Random;

public class GemFinder {

    public static boolean getProc(int level) {
        Random rand = new Random();
        int chance = 0;

        switch (level) {
            case 1:
                chance = 3;
                break;
            case 2:
                chance = 6;
                break;
            case 3:
                chance = 10;
                break;
            case 4:
                chance = 15;
                break;
            case 5:
                chance = 20;
                break;
            default:
                return false;
        }

        return rand.nextInt(100) < chance;
    }

    public static int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(1000) + 1;
    }
}

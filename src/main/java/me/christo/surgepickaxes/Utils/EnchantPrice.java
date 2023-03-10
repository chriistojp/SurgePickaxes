package me.christo.surgepickaxes.Utils;

public enum EnchantPrice {

    EFFICIENCY(1, 2, 3, 4, 5, 0),
    FORTUNE(10, 20, 30, 0, 0, 0),
    GEMFINDER(15, 25, 35, 45, 55, 0),
    JACKPOT(25, 50, 75, 100, 125, 0),
    SHATTERPROOF(8, 0, 0, 0, 0, 0),

    RAMPAGE(1, 0, 0, 0, 0, 0),

    GREED(1, 0, 0, 0, 0, 0);

    private final int[] prices;


    EnchantPrice(int level1, int level2, int level3, int level4, int level5, int level6) {
        prices = new int[] { level1, level2, level3, level4, level5, level6};
    }



    public static int getPrice(String enchant, int level) {
        //level 4
        try {
            EnchantPrice e = EnchantPrice.valueOf(enchant.toUpperCase());
            return e.prices[level - 1];
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid enchantment or level.");
        }
    }

}

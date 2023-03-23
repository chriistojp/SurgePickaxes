package me.christo.surgepickaxes.Utils;

public enum EnchantPrice {

    EFFICIENCY(50, 200, 500, 1000, 3000, 0),
    FORTUNE(750, 1500, 3000, 0, 0, 0),
    GEMFINDER(100, 400, 1000, 2000, 4000, 0),
    JACKPOT(100, 400, 1000, 2000, 4000, 0),
    SHATTERPROOF(500, 0, 0, 0, 0, 0),

    RAMPAGE(5000, 0, 0, 0, 0, 0),

    GREED(5000, 0, 0, 0, 0, 0);

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

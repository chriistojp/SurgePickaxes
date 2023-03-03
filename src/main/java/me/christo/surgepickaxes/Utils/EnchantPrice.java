package me.christo.surgepickaxes.Utils;

public enum EnchantPrice {

    FORTUNE(10, 20, 30, 40, 50),
    EFFICIENCY(5, 10, 15, 20, 25),
    GEMFINDER(15, 25, 35, 45, 55),
    JACKPOT(25, 50, 75, 100, 125),
    SHATTERPROOF(8, 16, 24, 32, 40),

    RAMPAGE(1, 1, 1, 1, 1),

    GREED(1, 1, 1, 1, 1);

    private final int[] prices;

    EnchantPrice(int level1, int level2, int level3, int level4, int level5) {
        prices = new int[] { level1, level2, level3, level4, level5 };
    }



    public static int getPrice(String enchant, int level) {
        try {
            EnchantPrice e = EnchantPrice.valueOf(enchant.toUpperCase());
            return e.prices[level+1];
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid enchantment or level.");
        }
    }

}

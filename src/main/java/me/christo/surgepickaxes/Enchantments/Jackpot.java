package me.christo.surgepickaxes.Enchantments;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Jackpot {

    public static boolean getProc(int level) {
        Random rand = new Random();
        int chance = 0;

        switch (level) {
            case 1:
                chance = 3;
                break;
            case 2:
                chance = 5;
                break;
            case 3:
                chance = 8;
                break;
            case 4:
                chance = 10;
                break;
            case 5:
                chance = 12;
                break;
            default:
                return false;
        }

        return rand.nextInt(100) < chance;
    }

    public static String getJackpotItem(int level) {
        Random rand = new Random();
        int itemIndex = rand.nextInt(25);
        int tier = (itemIndex / 5) + 1; // Calculate the tier of the item

        List<String> items = Arrays.asList(
                "give @p diamond 1",
                "give @p gold_block 5",
                "give @p iron_block 10",
                "give @p emerald_block 5",
                "give @p diamond_block 2",
                "give @p diamond 3"
                // Add more commands as needed
        );

        List<Double> tierProbabilities = Arrays.asList(
                0.2, 0.3, 0.3, 0.15, 0.05, // Tier 1 probabilities
                0.15, 0.25, 0.35, 0.2, 0.05, // Tier 2 probabilities
                0.1, 0.2, 0.4, 0.2, 0.1, // Tier 3 probabilities
                0.05, 0.15, 0.5, 0.2, 0.1, // Tier 4 probabilities
                0.05, 0.1, 0.5, 0.25, 0.1 // Tier 5 probabilities
        ).subList((tier - 1) * 5, tier * 5);

        for (int i = 1; i <= level; i++) {
            double totalProb = 0.0;
            for (double probability : tierProbabilities) {
                probability += (i - 1) * 0.0  - 1; // Increase probability by 1% for each level above 1
                totalProb += probability;
            }
            double randNum = rand.nextDouble() * totalProb;
            double probAccum = 0.0;
            for (int j = tierProbabilities.size() - 1; j >= 0; j--) {
                double probability = tierProbabilities.get(j) + ((i - 1) * 0.01); // Increase probability by 1% for each level above 1
                probAccum += probability;
                if (randNum < probAccum) {
                    itemIndex = (tier - 1) * 5 + j; // Select a random item from the tier
                    break;
                }
            }
        }

        String itemName = items.get(itemIndex);
        return itemName;
    }
}




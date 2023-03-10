package me.christo.surgepickaxes.Enchantments;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Rampage {


    private static final int DURATION = 10; // Duration of the Haste effect in seconds

    public static void enableHaste(Player player) {
        // Apply the Haste effect to the player
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, DURATION * 20, 30));
    }

    public static void disableHaste(Player player) {
        // Remove the Haste effect from the player
        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
    }

}


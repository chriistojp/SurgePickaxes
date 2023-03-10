package me.christo.surgepickaxes.Enchantments;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Greed {
    private static final int DURATION = 100000; // Duration of the "greed" effect in seconds
    private static final double MULTIPLIER = 2.0; // Multiplier for the "greed" effect
    private static final Map<UUID, Long> activePlayers = new HashMap<>(); // Map of active players and their expiration times

    public static void enable(Player player) {
        // Calculate the expiration time
        long expirationTime = System.currentTimeMillis() + (DURATION * 1000);

        // Add the player and expiration time to the activePlayers map
        activePlayers.put(player.getUniqueId(), expirationTime);
    }

    public static void disable(Player player) {
        // Remove the player from the activePlayers map
        activePlayers.remove(player.getUniqueId());
    }

    public static boolean isEnabled(Player player) {
        // Check if the player is in the activePlayers map
        if (activePlayers.containsKey(player.getUniqueId())) {
            // Check if the expiration time has passed
            long expirationTime = activePlayers.get(player.getUniqueId());
            if (System.currentTimeMillis() < expirationTime) {
                return true; // "Greed" effect is still active for the player
            }
            else {
                activePlayers.remove(player.getUniqueId()); // Remove the player from the activePlayers map
            }
        }
        return false; // "Greed" effect is not active for the player
    }

    public static double getMultiplier(Player player) {
        // If the "greed" effect is active for the player, return the multiplier, else return 1.0
        return isEnabled(player) ? MULTIPLIER : 1.0;
    }
}


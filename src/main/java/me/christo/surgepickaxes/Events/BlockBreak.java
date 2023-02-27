package me.christo.surgepickaxes.Events;

import me.christo.surgepickaxes.Enchantments.EnchantManager;
import me.christo.surgepickaxes.Handlers.ExperienceManager;
import me.christo.surgepickaxes.Handlers.NBTManager;
import me.christo.surgepickaxes.Handlers.Pickaxe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Locale;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Bukkit.broadcastMessage("0");
        Player player = event.getPlayer();
        Location loc = event.getBlock().getLocation();
        loc.setY(loc.getY() - 1);
        if(loc.getBlock().getType() == Material.BEDROCK) {
            System.out.println(1);
            if (player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
                System.out.println(2);
                Pickaxe pickaxe = new Pickaxe(player);
                if (pickaxe.isPickaxe()) {
                    System.out.println(3);
                    NBTManager manager = new NBTManager(player.getItemInHand());
                    EnchantManager.checkEnchants(player);
                    int xp = ExperienceManager.getExperience(event.getBlock().getType());
                    ExperienceManager.increaseExperience(player, xp, manager);

                }
            }
        }
    }
}

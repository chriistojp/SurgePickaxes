package me.christo.surgepickaxes.Events;

import me.christo.surgepickaxes.Enchantments.EnchantManager;
import me.christo.surgepickaxes.Enchantments.Greed;
import me.christo.surgepickaxes.Handlers.Experience.ExperienceManager;
import me.christo.surgepickaxes.Handlers.NBTManager;
import me.christo.surgepickaxes.Handlers.Pickaxe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location loc = event.getBlock().getLocation();
        loc.setY(loc.getY() - 1);
        if(loc.getBlock().getType() == Material.BEDROCK) {

            if (player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {

                Pickaxe pickaxe = new Pickaxe(player);
                if (pickaxe.isPickaxe(player)) {
                    if(Greed.isEnabled(player)) {
                        Block block = event.getBlock();
                        Collection<ItemStack> drops = block.getDrops();
                        for (ItemStack drop : drops) {
                            drop.setAmount(drop.getAmount() * 2);
                            block.getLocation().getWorld().dropItem(block.getLocation(), drop);
                        }


                    }
                    NBTManager manager = new NBTManager(player.getItemInHand());
                    EnchantManager.checkEnchants(player);
                    int xp = ExperienceManager.getExperience(event.getBlock().getType());
                    ExperienceManager.increaseExperience(player, xp, manager);

                    Bukkit.broadcastMessage("XP: " + xp + " Pickaxe XP: " + manager.getNBT("xp", PersistentDataType.INTEGER));
                }
            }
        }
    }

}

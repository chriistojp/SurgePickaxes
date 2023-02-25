package me.christo.surgepickaxes.Events;

import me.christo.surgepickaxes.Enchantments.EnchantManager;
import me.christo.surgepickaxes.Handlers.NBTManager;
import me.christo.surgepickaxes.Handlers.Pickaxe;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
            Pickaxe pickaxe = new Pickaxe(player);
            if(pickaxe.isPickaxe()) {
                EnchantManager.checkEnchants(player);
            }
        }
    }
}

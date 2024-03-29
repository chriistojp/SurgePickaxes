package me.christo.surgepickaxes.Events.LossPrevention;


import me.christo.surgepickaxes.Handlers.Pickaxe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxeListener implements Listener{

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if(Pickaxe.isPickaxe(event.getPlayer(), event.getItemDrop().getItemStack())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (Pickaxe.isPickaxe((Player) event.getWhoClicked())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {


        if (Pickaxe.isPickaxe((Player) event.getInitiator())) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        for (ItemStack item : event.getDrops()) {
            if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
                event.getDrops().remove(item);
            }
        }
    }

    private boolean isPickaxe(ItemStack item) {
        return item.getType().name().endsWith("PICKAXE") && item.getItemMeta() != null;
    }

    private boolean isPlayersPickaxe(Player player, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) {
            return false;
        }
        String displayName = ChatColor.stripColor(meta.getDisplayName());
        return displayName.contains(player.getName());
    }
}


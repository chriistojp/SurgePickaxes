package me.christo.surgepickaxes.Events.LossPrevention;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class PickxeListener implements Listener{

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (isPickaxe(item) && !isPlayersPickaxe(event.getPlayer(), item)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (isPickaxe(item) && !isPlayersPickaxe((Player) event.getWhoClicked(), item)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        ItemStack item = event.getItem();
        if (isPickaxe(item) && !isPlayersPickaxe((Player) event.getInitiator(), item)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        for (ItemStack item : event.getDrops()) {
            if (isPickaxe(item) && !isPlayersPickaxe(event.getEntity(), item)) {
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


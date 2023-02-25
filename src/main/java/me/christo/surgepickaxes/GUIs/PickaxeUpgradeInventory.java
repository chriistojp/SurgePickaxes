package me.christo.surgepickaxes.GUIs;

import me.christo.surgepickaxes.Handlers.MongoHandler;
import me.christo.surgepickaxes.Handlers.NBTManager;
import me.christo.surgepickaxes.Handlers.Pickaxe;
import me.christo.surgepickaxes.Utils.EnchantPrice;
import me.christo.surgepickaxes.Utils.Gui;
import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import tsp.headdb.core.api.HeadAPI;

import java.util.ArrayList;
import java.util.List;

public class PickaxeUpgradeInventory {


    //2190 up arrow - 11
    //14661 book - 13
    //14832 experience - 15


    public static void open(Player p) {

        //14673 up arrow - 11
        //14661 book - 13
        //14832 experience - 15
        Gui gui = new Gui("Pickaxe", 36);
        HeadAPI.getHeadById(14673).get().getItem(p.getUniqueId());
        gui.fill(Material.BLACK_STAINED_GLASS_PANE, " ");

        String unlocked = " &a&l[UNLOCKED]";
        String purchased = " &3&l[PURCHASED]";
        String locked = " &c&l[LOCKED]";

        String[] efficiencyLore = {
                "",
                "&7Efficiency allows you to break blocks faster.",
                "",
                "&7&l> &7Your Level: &e{level}",
                "&7&l> &7Cost to Upgrade: &e{cost} Gems",
                "&7&l> &7Max Level: &e 5",
                "",
                "&7&o(( Right-Click to Upgrade ))"
        };

        gui.i(11, getItem(p, HeadAPI.getHeadById(49711).get().getItem(p.getUniqueId()), "efficiency", "&e", "&7Efficiency allows you to break blocks faster."));
        gui.i(12, getItem(p, HeadAPI.getHeadById(49711).get().getItem(p.getUniqueId()), "fortune", "&5", "&7Fortune allows you to receive extra drops from ores."));








        gui.onClick(e -> {
            NBTManager manager = new NBTManager(p.getItemInHand());
            e.setCancelled(true);
            if(e.getSlot() == 10) {
                MongoHandler.setValue(p, "level", 0);
                manager.setNBT("level", PersistentDataType.INTEGER, 0);
            }
            if(e.getSlot() == 11) {
                MongoHandler.setValue(p, "efficiency", 1);
                manager.setNBT("efficiency", PersistentDataType.INTEGER, 1);
            }
            if(e.getSlot() == 12) {
                manager.setNBT("fortune", PersistentDataType.INTEGER, 1);
            }

        });
        gui.show(p);


    }
    public static ItemStack getItem(Player player, ItemStack item, String enchantment, String color, String... description) {

        Pickaxe pickaxe = new Pickaxe(player);
        String status;
        //efficieny: 1 pickaxe: 1
        //if enchantment level < pickaxeLevel
        status = (pickaxe.getEnchantmentLevel(player, enchantment) < pickaxe.getPickaxeLevel()) ? " &a&l[AVAILABLE]" : " &3&l[PURCHASED]";
        if(pickaxe.getPickaxeLevel() == 0) {
            status = " &c&l[LOCKED]";
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color(color + enchantment + status));
        List<String> lore = new ArrayList<>();
        lore.add("");
        for(String s : description) {
            lore.add(Util.color(s));
        }
        lore.add("");
        lore.add(Util.color(color + "&l> &7Your Level: &e" + color + pickaxe.getEnchantmentLevel(player, enchantment)));

        NBTManager manager = new NBTManager(player.getItemInHand());
        int level = manager.getNBT(enchantment, PersistentDataType.INTEGER);


        lore.add(Util.color(color + "&l> &7Cost to Upgrade: " + color + EnchantPrice.getPrice(enchantment, level)));
        lore.add(Util.color(color + "&l> &7Max Level: " + color + "5"));
        lore.add("");
        if(pickaxe.isEligibleForEnchantment(enchantment)) {
            lore.add(Util.color("&7&o (( Right-Click to Upgrade ))"));
        } else {
            lore.add(Util.color("&7&o&m (( Right-Click to Upgrade ))"));
        }


        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;



    }




}

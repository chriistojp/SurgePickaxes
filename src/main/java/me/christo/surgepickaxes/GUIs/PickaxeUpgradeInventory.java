package me.christo.surgepickaxes.GUIs;

import me.christo.surgepickaxes.Handlers.MongoHandler;
import me.christo.surgepickaxes.Handlers.NBTManager;
import me.christo.surgepickaxes.Handlers.Pickaxe;
import me.christo.surgepickaxes.Utils.EnchantPrice;
import me.christo.surgepickaxes.Utils.Gui;
import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
        gui.i(12, getItem(p, HeadAPI.getHeadById(803).get().getItem(p.getUniqueId()), "fortune", "&5", "&7Fortune allows you to receive extra drops from ores."));
        gui.i(13, getItem(p, HeadAPI.getHeadById(10178).get().getItem(p.getUniqueId()), "gemfinder", "&2", "&7Fortune allows you to receive extra drops from ores."));
        gui.i(14, getItem(p, HeadAPI.getHeadById(10250).get().getItem(p.getUniqueId()), "shatterproof", "&b", "&7Fortune allows you to receive extra drops from ores."));
        gui.i(15, getItem(p, HeadAPI.getHeadById(18628).get().getItem(p.getUniqueId()), "jackpot", "&6", "&7Fortune allows you to receive extra drops from ores."));

        gui.i(21, getItem(p, HeadAPI.getHeadById(17088).get().getItem(p.getUniqueId()), "rampage", "&c&l", "&7Fortune allows you to receive extra drops from ores."));
        gui.i(23, getItem(p, HeadAPI.getHeadById(23869).get().getItem(p.getUniqueId()), "greed", "&2&l", "&7Fortune allows you to receive extra drops from ores."));









        gui.onClick(e -> {
            NBTManager manager = new NBTManager(p.getItemInHand());
            e.setCancelled(true);
            if(e.getSlot() == 10) {
                //if player has enough gems
                int currentLevel = MongoHandler.getValue(p, "efficiency");
                MongoHandler.setValue(p, "efficiency", currentLevel + 1);
                manager.setNBT("efficiency", PersistentDataType.INTEGER, currentLevel + 1);
                p.getItemInHand().addEnchantment(Enchantment.DIG_SPEED, currentLevel + 1);
            }
            if(e.getSlot() == 11) {

                int currentLevel = MongoHandler.getValue(p, "fortune");
                MongoHandler.setValue(p, "fortune", currentLevel + 1);
                manager.setNBT("fortune", PersistentDataType.INTEGER, currentLevel + 1);
                p.getItemInHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, currentLevel + 1);

            }
            if(e.getSlot() == 12) {

                MongoHandler.setValue(p, "shatterproof", MongoHandler.getValue(p, "shatterproof") + 1);
                manager.setNBT("shatterproof", PersistentDataType.INTEGER, manager.getNBT("shatterproof", PersistentDataType.INTEGER) + 1);

            }
            if(e.getSlot() == 12) {

                MongoHandler.setValue(p, "shatterproof", MongoHandler.getValue(p, "shatterproof") + 1);
                manager.setNBT("shatterproof", PersistentDataType.INTEGER, manager.getNBT("shatterproof", PersistentDataType.INTEGER) + 1);

            }
            if(e.getSlot() == 12) {

                MongoHandler.setValue(p, "shatterproof", MongoHandler.getValue(p, "shatterproof") + 1);
                manager.setNBT("shatterproof", PersistentDataType.INTEGER, manager.getNBT("shatterproof", PersistentDataType.INTEGER) + 1);

            }

            if(e.getSlot() == 12) {

                MongoHandler.setValue(p, "shatterproof", MongoHandler.getValue(p, "shatterproof") + 1);
                manager.setNBT("shatterproof", PersistentDataType.INTEGER, manager.getNBT("shatterproof", PersistentDataType.INTEGER) + 1);

            }

            if(e.getSlot() == 12) {

                MongoHandler.setValue(p, "shatterproof", MongoHandler.getValue(p, "shatterproof") + 1);
                manager.setNBT("shatterproof", PersistentDataType.INTEGER, manager.getNBT("shatterproof", PersistentDataType.INTEGER) + 1);

            }

        });
        gui.show(p);


    }
    public static ItemStack getItem(Player player, ItemStack item, String enchantment, String color, String... description) {



        Pickaxe pickaxe = new Pickaxe(player);
        String status;

        NBTManager manager = new NBTManager(player.getItemInHand());
        int level = manager.getNBT(enchantment, PersistentDataType.INTEGER);
        Bukkit.broadcastMessage("1");

        //efficieny: 1 pickaxe: 1
        //if enchantment level < pickaxeLevel
        status = (pickaxe.getEnchantmentLevel(player, enchantment) < pickaxe.getPickaxeLevel()) ? " &a&l[AVAILABLE]" : " &3&l[PURCHASED]";
        if(pickaxe.getPickaxeLevel() == 0) {
            status = " &c&l[LOCKED]";
        }
        ItemMeta meta = item.getItemMeta();
        if(enchantment.equals("shatterproof") && level == 1) {
            status = " &3&l[PURCHASED]";
        }
        meta.setDisplayName(Util.color(color + enchantment + status));
        List<String> lore = new ArrayList<>();
        lore.add("");
        for(String s : description) {
            lore.add(Util.color(s));
        }
        lore.add("");
        lore.add(Util.color(color + "&l> &7Your Level: &e" + color + pickaxe.getEnchantmentLevel(player, enchantment)));




        Bukkit.broadcastMessage("level " + level + "");
        Bukkit.broadcastMessage("cost: " + EnchantPrice.getPrice(enchantment, level));
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

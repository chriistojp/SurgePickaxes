package me.christo.surgepickaxes.GUIs;

import me.christo.surgepickaxes.Handlers.*;
import me.christo.surgepickaxes.Handlers.Experience.ExperienceManager;
import me.christo.surgepickaxes.Handlers.Experience.PickaxeUpgradeExperience;
import me.christo.surgepickaxes.Main;
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
import skysurge.net.Objects.SurgePlayer;
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

        gui.i(11, getItem(p, HeadAPI.getHeadById(49711).get().getItem(p.getUniqueId()), "efficiency", "&e", "&7Efficiency allows you to break blocks faster."));
        gui.i(12, getItem(p, HeadAPI.getHeadById(803).get().getItem(p.getUniqueId()), "fortune", "&5", "&7Fortune allows you to receive extra drops from ores."));
        gui.i(13, getItem(p, HeadAPI.getHeadById(10178).get().getItem(p.getUniqueId()), "gemfinder", "&2", "&7Fortune allows you to receive extra drops from ores."));
        gui.i(14, getItem(p, HeadAPI.getHeadById(10250).get().getItem(p.getUniqueId()), "shatterproof", "&b", "&7Fortune allows you to receive extra drops from ores."));
        gui.i(15, getItem(p, HeadAPI.getHeadById(18628).get().getItem(p.getUniqueId()), "jackpot", "&6", "&7Fortune allows you to receive extra drops from ores."));


        gui.i(21, getItem(p, HeadAPI.getHeadById(17088).get().getItem(p.getUniqueId()), "rampage", "&c&l", "&7Fortune allows you to receive extra drops from ores."));
        gui.i(23, getItem(p, HeadAPI.getHeadById(23869).get().getItem(p.getUniqueId()), "greed", "&2&l", "&7Fortune allows you to receive extra drops from ores."));



        NBTManager manager = new NBTManager(p.getItemInHand());
        if(manager.getNBT("level", PersistentDataType.INTEGER) == 5) {

            ItemStack item = new ItemStack(p.getItemInHand());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + Util.color(" &6&l[MAX LEVEL]"));
            item.setItemMeta(meta);
            gui.i(22, item);

        } else if(ExperienceManager.hasEnoughExperience(manager.getNBT("level", PersistentDataType.INTEGER) + 1, manager.getNBT("xp", PersistentDataType.INTEGER))) {
            ItemStack item = new ItemStack(p.getItemInHand());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + Util.color(" &a&l[UPGRADE]"));
            List<String> lore = meta.getLore();
            lore.add("");
            lore.add(Util.color("&3&l[COST] » &7") + UpgradeCost.getCostForLevel(manager.getNBT("level", PersistentDataType.INTEGER) + 1) + " Gems");
            meta.setLore(lore);
            item.setItemMeta(meta);
            gui.i(22, item);
        } else {


            int remainingXP = PickaxeUpgradeExperience.getCostForLevel(manager.getNBT("level", PersistentDataType.INTEGER) + 1) - manager.getNBT("xp", PersistentDataType.INTEGER);


            ItemStack item = new ItemStack(p.getItemInHand());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + Util.color(" &c&l[UNAVAILABLE]"));
            List<String> lore = meta.getLore();
            lore.add("");
            lore.add(Util.color("&c&l[XP REMAINING] &7» &c" + remainingXP + " &7XP"));
            meta.setLore(lore);
            item.setItemMeta(meta);
            gui.i(22, item);
        }











        gui.onClick(e -> {
           // NBTManager manager = new NBTManager(p.getItemInHand());
            e.setCancelled(true);

            Pickaxe pickaxe = new Pickaxe(p);
            SurgePlayer player = new SurgePlayer(p.getUniqueId());

            if(e.getSlot() == 11) {
                //if player has enough gems
                if(pickaxe.isEligibleForEnchantment("efficiency")) {
                    int currentLevel = manager.getNBT("efficiency", PersistentDataType.INTEGER);
                    MongoHandler.setValue(p, "efficiency", currentLevel + 1);
                    manager.setNBT("efficiency", PersistentDataType.INTEGER, currentLevel + 1);
                    p.getItemInHand().addEnchantment(Enchantment.DIG_SPEED, currentLevel + 1);
                    Pickaxe.refreshPickaxeEnchants(p);


                    gui.i(11, getItem(p, HeadAPI.getHeadById(49711).get().getItem(p.getUniqueId()), "efficiency", "&e", "&7Fortune allows you to receive extra drops from ores."));

                    p.sendMessage(Util.color(Main.getInstance().getConfig().getString("messages.upgradedEnchantment")).replace("%enchantment%", "Efficiency").replace("%level%", (currentLevel + 1) + ""));


                }
            }
            if(e.getSlot() == 12) {

           
                if(pickaxe.isEligibleForEnchantment("fortune")) {

                    int currentLevel = manager.getNBT("fortune", PersistentDataType.INTEGER);
                    if(EnchantPrice.getPrice("fortune", currentLevel + 1) >= player.getGems()) {

                        MongoHandler.setValue(p, "fortune", currentLevel + 1);
                        manager.setNBT("fortune", PersistentDataType.INTEGER, currentLevel + 1);
                        p.getItemInHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, currentLevel + 1);
                        Pickaxe.refreshPickaxeEnchants(p);


                        gui.i(12, getItem(p, HeadAPI.getHeadById(803).get().getItem(p.getUniqueId()), "fortune", "&5", "&7Fortune allows you to receive extra drops from ores."));
                        player.setGems(player.getGems() - EnchantPrice.getPrice("fortune", currentLevel));
                        p.sendMessage(Util.color(Main.getInstance().getConfig().getString("messages.upgradedEnchantment")).replace("%enchantment%", "Fortune").replace("%level%", (currentLevel + 1) + ""));
                    }

                }

            }
            if(e.getSlot() == 13) {

                if(pickaxe.isEligibleForEnchantment("gemfinder")) {
                    int currentLevel = manager.getNBT("gemfinder", PersistentDataType.INTEGER);
                    if(EnchantPrice.getPrice("gemfinder", currentLevel + 1) >= player.getGems()) {
                        MongoHandler.setValue(p, "gemfinder", currentLevel + 1);
                        manager.setNBT("gemfinder", PersistentDataType.INTEGER, currentLevel + 1);
                        Pickaxe.refreshPickaxeEnchants(p);


                        gui.i(13, getItem(p, HeadAPI.getHeadById(10178).get().getItem(p.getUniqueId()), "gemfinder", "&2", "&7Fortune allows you to receive extra drops from ores."));
                        player.setGems(player.getGems() - EnchantPrice.getPrice("gemfinder", currentLevel));
                        p.sendMessage(Util.color(Main.getInstance().getConfig().getString("messages.upgradedEnchantment")).replace("%enchantment%", "Gem Finder").replace("%level%", (currentLevel + 1) + ""));
                    }

                }

            }
            if(e.getSlot() == 14) {

                if(pickaxe.isEligibleForEnchantment("shatterproof")) {
                    int currentLevel = manager.getNBT("shatterproof", PersistentDataType.INTEGER);
                    if(EnchantPrice.getPrice("shatterproof", currentLevel + 1) >= player.getGems()) {
                        MongoHandler.setValue(p, "shatterproof", currentLevel + 1);
                        manager.setNBT("shatterproof", PersistentDataType.INTEGER, currentLevel + 1);
                        Pickaxe.refreshPickaxeEnchants(p);


                        gui.i(14, getItem(p, HeadAPI.getHeadById(10250).get().getItem(p.getUniqueId()), "shatterproof", "&b", "&7Fortune allows you to receive extra drops from ores."));
                        player.setGems(player.getGems() - EnchantPrice.getPrice("shatterproof", currentLevel));
                        p.sendMessage(Util.color(Main.getInstance().getConfig().getString("messages.upgradedEnchantment")).replace("%enchantment%", "Shatter Proof").replace("%level%", (currentLevel + 1) + ""));
                    }

                }

            }

            if(e.getSlot() == 15) {
                
                if(pickaxe.isEligibleForEnchantment("jackpot")) {
                    int currentLevel = manager.getNBT("jackpot", PersistentDataType.INTEGER);
                    if(EnchantPrice.getPrice("jackpot", currentLevel + 1) >= player.getGems()) {
                        MongoHandler.setValue(p, "jackpot", currentLevel + 1);
                        manager.setNBT("jackpot", PersistentDataType.INTEGER, currentLevel + 1);
                        Pickaxe.refreshPickaxeEnchants(p);


                        gui.i(15, getItem(p, HeadAPI.getHeadById(18628).get().getItem(p.getUniqueId()), "jackpot", "&6", "&7Fortune allows you to receive extra drops from ores."));
                        player.setGems(player.getGems() - EnchantPrice.getPrice("jackpot", currentLevel));
                        p.sendMessage(Util.color(Main.getInstance().getConfig().getString("messages.upgradedEnchantment")).replace("%enchantment%", "Jackpot").replace("%level%", (currentLevel + 1) + ""));
                    }

                }
             

            }

            if(e.getSlot() == 22) {

                if(e.getCurrentItem().getItemMeta().getDisplayName().contains("UNAVAILABLE")) {
                    e.setCancelled(true);
                } else {
                    int level = new NBTManager(p.getItemInHand()).getNBT("level", PersistentDataType.INTEGER);
                    int cost = UpgradeCost.getCostForLevel(level + 1);
                    player.addGems(10000);
                    if(player.getGems() > cost) {
                        if(level < 5) {
                            new NBTManager(p.getItemInHand()).setNBT("level",  PersistentDataType.INTEGER, level + 1);
                            new NBTManager(p.getItemInHand()).setNBT("xp",  PersistentDataType.INTEGER, 0);
                            MongoHandler.setValue(p, "level", level + 1);
                            Pickaxe.refreshPickaxeLevel(p);

                            //sendmessage upgrade
                            gui.close(p);

                        }

                    }

                }


            }

        });
        gui.show(p);


    }
    public static ItemStack getItem(Player player, ItemStack item, String enchantment, String color, String... description) {



        Pickaxe pickaxe = new Pickaxe(player);
        String status;

        NBTManager manager = new NBTManager(player.getItemInHand());
        int level = manager.getNBT(enchantment, PersistentDataType.INTEGER);

        //efficieny: 1 pickaxe: 1
        //if enchantment level < pickaxeLevel
        status = (pickaxe.getEnchantmentLevel(player, enchantment) < pickaxe.getPickaxeLevel()) ? " &a&l[AVAILABLE]" : " &3&l[PURCHASED]";
        if(pickaxe.getPickaxeLevel() == 0) {
            status = " &c&l[LOCKED]";
        }
        if(pickaxe.getPickaxeLevel() != 5 && enchantment.equals("greed")) {
            status = " &c&l[LOCKED]";
        }
        if(pickaxe.getPickaxeLevel() != 5 && enchantment.equals("rampage")) {
            status = " &c&l[LOCKED]";
        }
        ItemMeta meta = item.getItemMeta();
        if(enchantment.equals("shatterproof") && level == 1) {
            status = " &3&l[PURCHASED]";
        }
        if(enchantment.equals("fortune") && level == 3) {
            status = " &3&l[PURCHASED]";
        }
        String name = enchantment + status;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        meta.setDisplayName(Util.color(color + name));
        List<String> lore = new ArrayList<>();
        lore.add("");
        for(String s : description) {
            lore.add(Util.color(s));
        }
        lore.add("");
        lore.add(Util.color(color + "&l> &7Your Level: &e" + color + pickaxe.getEnchantmentLevel(player, enchantment)));
        lore.add(Util.color(color + "&l> &7Cost to Upgrade: " + color + EnchantPrice.getPrice(enchantment, level + 1)));
        if(enchantment.equals("shatterproof") || enchantment.equals("fortune")) {
            if(enchantment.equals("shatterproof")) {
                lore.add(Util.color(color + "&l> &7Max Level: " + color + "1"));
            } else {
                lore.add(Util.color(color + "&l> &7Max Level: " + color + "3"));
            }
        } else {
            lore.add(Util.color(color + "&l> &7Max Level: " + color + "5"));
        }
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

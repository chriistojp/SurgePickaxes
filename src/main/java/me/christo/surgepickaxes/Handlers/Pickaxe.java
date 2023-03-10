package me.christo.surgepickaxes.Handlers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.christo.surgepickaxes.Enchantments.EnchantManager;
import me.christo.surgepickaxes.Utils.Util;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class Pickaxe {

    //    int efficiencyLevel;
//    int fortuneLevel;
//    int gemFinderLevel;
//    int rampageLevel;
//    int greedLevel;
//    int jackPotLevel;
//    int shatterProofLevel;
    Player player;

    public Pickaxe(Player player) {
        this.player = player;
    }

    public int getPickaxeLevel() {

        NBTManager manager = new NBTManager(player.getItemInHand());

        return manager.getNBT("level", PersistentDataType.INTEGER);

    }

    public boolean isPickaxe(Player player) {
        if (player.getItemInHand() != null) {
            if (player.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
                ItemStack item = player.getItemInHand();
                ItemMeta meta = item.getItemMeta();
                String name = ChatColor.stripColor(meta.getDisplayName());
                if (name.contains(player.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getEnchantmentLevel(Player p, String enchantment) {

        NBTManager manager = new NBTManager(p.getItemInHand());
        return manager.getNBT(enchantment, PersistentDataType.INTEGER);
    }

    public int addEnchantmentLevel(String enchantment) {

        try (MongoClient client = MongoConnection.acquire()) {
            MongoDatabase database = client.getDatabase("Pickaxes");
            MongoCollection<org.bson.Document> collection = database.getCollection("Pickaxes");
            org.bson.Document query = new org.bson.Document("name", "John Doe");
            Document result = collection.find(query).first();
            if (result != null) {
                // Do something with the result...
            }
        }


        return 0;
    }

    public boolean isEligibleForEnchantment(String enchantment) {

        //if pickaxe level > enchantment level [available]

        if (enchantment.equals("rampage") && getEnchantmentLevel(player, "rampage") == 1) {
            return false;
        }
        if (enchantment.equals("greed") && getEnchantmentLevel(player, "greed") == 1) {
            return false;
        }

        if (enchantment.equals("rampage") || enchantment.equals("greed") && getPickaxeLevel() != 5) {
            return false;
        }

        if (enchantment.equals("fortune") && getEnchantmentLevel(player, "fortune") == 3) {
            return false;
        }

        if(enchantment.equals("shatterproof") && getEnchantmentLevel(player, "shatterproof") == 1) {
            return false;
        }

        if (getEnchantmentLevel(player, "level") > getEnchantmentLevel(player, enchantment)) {
            return true;
        } else {
            return false;
        }


    }

    public boolean hasPurchasedEnchantment(String enchantment) {

        //if pickaxe level == enchantment level

        return false;
    }

    public static boolean checkForPicaxe(Player player) {


        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) != null) {
                if (player.getInventory().getItem(i).getType() == Material.DIAMOND_PICKAXE) {
                    ItemStack item = player.getInventory().getItem(i);
                    ItemMeta meta = item.getItemMeta();
                    Bukkit.broadcastMessage(meta.getDisplayName());
                    String name = ChatColor.stripColor(meta.getDisplayName());
                    if (name.contains(player.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static void refreshPickaxeLevel(Player player) {
        NBTManager manager = new NBTManager(player.getItemInHand());
        int level = manager.getNBT("level", PersistentDataType.INTEGER);
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color("&b&l" + player.getName() + "'s Pickaxe &7(Level " + level + "&7)"));
        item.setItemMeta(meta);
    }

    public static void refreshPickaxeEnchants(Player player) {
        NBTManager manager = new NBTManager(player.getItemInHand());
        int level = manager.getNBT("level", PersistentDataType.INTEGER);
        int efficiencyLevel = manager.getNBT("efficiency", PersistentDataType.INTEGER);
        int fortuneLevel = manager.getNBT("fortune", PersistentDataType.INTEGER);
        int gemFinderLevel = manager.getNBT("gemfinder", PersistentDataType.INTEGER);
        int shatterProofLevel = manager.getNBT("shatterproof", PersistentDataType.INTEGER);
        int jackpotLevel = manager.getNBT("jackpot", PersistentDataType.INTEGER);


        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        Bukkit.broadcastMessage(meta.getLore().get(0));
        List<String> lore = meta.getLore();
        lore.set(0, Util.color("&7Efficiency " + efficiencyLevel));
        lore.set(1, Util.color("&7Fortune " + fortuneLevel));
        lore.set(2, Util.color("&7Gem Finder " + gemFinderLevel));
        lore.set(3, Util.color("&7Shatter Proof " + shatterProofLevel));
        lore.set(4, Util.color("&7Jackpot " + jackpotLevel));

        meta.setLore(lore);
        item.setItemMeta(meta);


    }

    public static void givePickaxe(Player p) {

        //check if they exist in the mongo db
        //if they do then get their values, if not set.

        int level = MongoHandler.getValue(p, "level");
        int efficiencyLevel = MongoHandler.getValue(p, "efficiency");
        int fortuneLevel = MongoHandler.getValue(p, "fortune");
        int gemFinderLevel = MongoHandler.getValue(p, "gemfinder");
        int shatterProofLevel = MongoHandler.getValue(p, "shatterproof");
        int jackpotLevel = MongoHandler.getValue(p, "jackpot");

        ItemStack item = new ItemStack(Util.createItem(Material.DIAMOND_PICKAXE, "&b&l" + p.getName() + "'s Pickaxe &7(Level " + level + "&7)",
                "&7Efficiency " + efficiencyLevel, "&7Fortune " + fortuneLevel, "&7Gem Finder " + gemFinderLevel, "&7Shatterproof " + shatterProofLevel, "&7Jackpot " + jackpotLevel,
                 "", "&c&lActive &7&o(Shift + Right Click to Cycle)", "&aRampage &7→ For &a10s &7all blocks will be &a&ninstantly broken.", "&aGreed &7→ For &a10s &7all drops will be &a&ndoubled."));
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        NBTManager nbtManager = new NBTManager(item);
        nbtManager.setNBT("level", PersistentDataType.INTEGER, 0);
        nbtManager.setNBT("xp", PersistentDataType.INTEGER, 0);
        nbtManager.setNBT("efficiency", PersistentDataType.INTEGER, 0);
        nbtManager.setNBT("fortune", PersistentDataType.INTEGER, 0);
        nbtManager.setNBT("shatterproof", PersistentDataType.INTEGER, 0);
        nbtManager.setNBT("jackpot", PersistentDataType.INTEGER, 0);
        nbtManager.setNBT("rampage", PersistentDataType.INTEGER, 0);
        nbtManager.setNBT("gemfinder", PersistentDataType.INTEGER, 0);
        nbtManager.setNBT("greed", PersistentDataType.INTEGER, 0);


        p.getInventory().addItem(item);


    }
}

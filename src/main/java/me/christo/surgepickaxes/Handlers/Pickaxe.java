package me.christo.surgepickaxes.Handlers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.christo.surgepickaxes.Utils.Util;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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

    public boolean isPickaxe() {
        if(player.getItemOnCursor().getType() == Material.DIAMOND_PICKAXE) {
            ItemStack item = player.getItemOnCursor();
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            String name = ChatColor.stripColor(meta.getDisplayName());
            if(name.contains(player.getName())) {
                return true;
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

        if(getEnchantmentLevel(player, "level") > getEnchantmentLevel(player, enchantment)) {
            return true;
        } else {
            return false;
        }


    }

    public boolean hasPurchasedEnchantment(String enchantment) {

        //if pickaxe level == enchantment level

        return false;
    }
    public static void givePickaxe(Player p) {

        //check if they exist in the mongo db
        //if they do then get their values, if not set.

        ItemStack item = new ItemStack(Util.createItem(Material.DIAMOND_PICKAXE, "&b&l" + p.getName() + "'s Pickaxe",
                "&7Efficiency 1", "&7Fortune 1", "" , "&b&lPassive &7&o(Always Active)", "&7Gem Finder 1", "&7Shatter Proof 1",
                "", "&c&lActive &7&o(Shift + Right Click to Cycle)", "&aRampage &7→ For &a10s &7all blocks will be &a&ninstantly broken.", "&aGreed &7→ For &a10s &7all drops will be &a&ndoubled."));
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

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

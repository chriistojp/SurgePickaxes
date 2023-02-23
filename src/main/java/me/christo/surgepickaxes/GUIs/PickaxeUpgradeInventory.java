package me.christo.surgepickaxes.GUIs;

import me.christo.surgepickaxes.Handlers.Pickaxe;
import me.christo.surgepickaxes.Utils.Gui;
import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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

        gui.i(11, HeadAPI.getHeadById(49711).get().getItem(p.getUniqueId()), "&e&lEfficiency" + unlocked, efficiencyLore);
        gui.i(12, HeadAPI.getHeadById(49711).get().getItem(p.getUniqueId()), "&e&lEfficiency" + purchased, efficiencyLore);
        gui.i(13, HeadAPI.getHeadById(49711).get().getItem(p.getUniqueId()), "&e&lEfficiency" + locked, efficiencyLore);







        gui.show(p);


    }
    public void getItem(Player player, ItemStack item, String enchantment, String color, int upgradeCost, String... description) {

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
        lore.add(color + "&l> &7Your Level: &e" + color + pickaxe.getEnchantmentLevel(player, enchantment));
        lore.add(color + "&l> &7Cost to Upgrade: " + color + pickaxe.getEnchantmentLevel(player, enchantment));
        lore.add(color + "&l> &7Your Level: " + color + pickaxe.getEnchantmentLevel(player, enchantment));
        lore.add(color + "&l> &7Max Level: " + color + "5");
        lore.add("");
        if(pickaxe.isEligibleForEnchantment(enchantment)) {
            lore.add("&7&o (( Right-Click to Upgrade ))");
        } else {
            lore.add("&7&o&s (( Right-Click to Upgrade ))");
        }


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

    }




}

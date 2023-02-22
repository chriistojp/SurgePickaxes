package me.christo.surgepickaxes.Handlers;

import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxeHandler {

    //pickaxe special enchants
    //passive:
    //gem finder

    //active:
    //Rampage (haste or double the efficiencyy)
    //Greed (all drops will be double)


    /**
     * Christo's Pickaxe
     *
     * Efficency 1
     * Fortune 2
     * Unbreaking 1
     *
     * &b&lPassive &7&o(Always Active)
     * Gem Finder 1
     *
     * &b&lActive &7&o(Shift + Right Click to Cycle)
     * &bRampage &7→ &bBlocks will be instantly broken for &b10s.
     * &bGreed &7→ For &b10s &7all drops will be &b&ndoubled.
     *
     *
     * @param p
     */


    public static void givePickaxe(Player p) {

        //check if they exist in the mongo db
        //if they do then get their values, if not set.

        ItemStack item = new ItemStack(Util.createItem(Material.DIAMOND_PICKAXE, "&b&l" + p.getName() + "'s Pickaxe",
                 "&7Efficiency 1", "&7Fortune 1", "" , "&b&lPassive &7&o(Always Active)", "&7Gem Finder 1", "&7Shatter Proof 1",
                "", "&c&lActive &7&o(Shift + Right Click to Cycle)", "&aRampage &7→ For &a10s &7all blocks will be &a&ninstantly broken.", "&aGreed &7→ For &a10s &7all drops will be &a&ndoubled."));
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        p.getInventory().addItem(item);



    }

}

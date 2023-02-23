package me.christo.surgepickaxes.GUIs;

import me.christo.surgepickaxes.Utils.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import tsp.headdb.core.api.HeadAPI;

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

}

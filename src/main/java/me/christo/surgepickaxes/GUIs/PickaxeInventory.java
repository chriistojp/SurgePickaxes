package me.christo.surgepickaxes.GUIs;

import me.christo.surgepickaxes.Utils.Gui;
import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tsp.headdb.core.api.HeadAPI;

public class PickaxeInventory {

    public static void open(Player p) {

        //14673 up arrow - 11
        //14661 book - 13
        //14832 experience - 15
        Gui gui = new Gui("Pickaxe", 27);
        HeadAPI.getHeadById(14673).get().getItem(p.getUniqueId());
        gui.fill(Material.BLACK_STAINED_GLASS_PANE, " ");
        gui.i(11, HeadAPI.getHeadById(14673).get().getItem(p.getUniqueId()), "&d&lEnchantments",
                Util.formatString(50 ,"", "&7Here you can upgrade your pickaxe. Pickaxe upgrades", "&7max out at level 5. To unlock each level your pickaxe",
                "&7level must first meet that specified level.", "&7For example you cannot buy &dFortune 3 &7without", "&7your pickaxe being &dLevel 3.", "", "&7Information about each enchantmenet can be found on",
                "&7the &dnext page.", "", "&7&o((Right-Click to Open))"));

        gui.i(13, HeadAPI.getHeadById(14661).get().getItem(p.getUniqueId()), "&6&lInformation",
                Util.formatString(50, "", "&7Here you can do things like &6upgrade your pickaxe.", "&7Upgrading your pickaxe can help you gain", "&7resources and island value faster.", "&7Pickaxe enchantments only work on &6Ore-Field &7generated blocks.", "", "&7If you have any questions feel free", "&6to ask a staff member."));



        gui.show(p);
    }

}

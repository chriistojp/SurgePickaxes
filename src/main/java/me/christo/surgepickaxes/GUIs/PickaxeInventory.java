package me.christo.surgepickaxes.GUIs;

import me.christo.surgepickaxes.Handlers.Pickaxe;
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

        String[] enchantLore = {
                "",
                "&7Here you can upgrade your pickaxe. Pickaxe upgrades",
                "&7max out at level 5. To unlock each level your pickaxe",
                "&7must first meet that specified level. For example you",
                "&7cannot buy &dFortune 3 &7without your pickaxe being",
                "&dLevel 3.",
                "",
                "&7Information about each enchantment can be found on the",
                "&dnext page.",
                "",
                "&7&o(( Right-Click to Open ))"
        };

        String[] informationLore = {
                "",
                "&7Here you can do things like &6upgrade &7your pickaxe.",
                "&7Upgrading your pickaxe can help you gain resources",
                "&7and island value faster. Custom pickaxe enchantments only work",
                "&7on &6Ore-Field &7generated blocks.",
                "",
                "&7If you have any questions feel free to &6ask a staff member."



        };

        String[] experienceLore = {
                "",
                "&7Different block give different amounts of &eexperience",
                "&7when you break them.",
                "",
                "&7Stone - &e1",
                "&8Coal Ore &7- &e5",
                "&fIron Ore &7- &e10",
                "&6Gold Ore &7- &e20",
                "&bDiamond Ore &7- &e50",
                "&aEmerald Ore &7- &e100",
                "",
                "&f&lIron Block &7- &e125",
                "&6&lGold Block &7- &e150",
                "&b&lDiamond Block &7- &e200",
                "&a&lEmerald Block &7- &e300"

        };

        gui.i(11, HeadAPI.getHeadById(14673).get().getItem(p.getUniqueId()), "&d&lEnchantments", enchantLore);



        gui.i(13, HeadAPI.getHeadById(14661).get().getItem(p.getUniqueId()), "&6&lInformation", informationLore);

        gui.i(15, HeadAPI.getHeadById(14832).get().getItem(p.getUniqueId()), "&e&lExperience", experienceLore);


        gui.onClick(e -> {
            e.setCancelled(true);
            if(e.getSlot() == 11) {
                gui.close(p);
                PickaxeUpgradeInventory.open(p);
            }
        });


        gui.show(p);
    }

}

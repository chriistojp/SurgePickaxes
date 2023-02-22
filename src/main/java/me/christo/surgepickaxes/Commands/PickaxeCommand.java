package me.christo.surgepickaxes.Commands;

import me.christo.surgepickaxes.GUIs.PickaxeInventory;
import me.christo.surgepickaxes.Handlers.PickaxeHandler;
import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tsp.headdb.core.api.HeadAPI;

public class PickaxeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            if(args.length == 0) {
                Player player = (Player) sender;

                PickaxeHandler.givePickaxe(player);
                PickaxeInventory.open(player);
                for(String s :  Util.formatString(50, "", "&7Here you can do things like &6upgrade your pickaxe.", "&7Upgrading your pickaxe can help you gain", "&7resources and island value faster.", "&7Pickaxe enchantments only work on &6Ore-Field &7generated blocks.", "", "&7If you have any questions feel free", "&6to ask a staff member.")) {
                    System.out.println(s);
                }

            }
        }

        return false;
    }
}

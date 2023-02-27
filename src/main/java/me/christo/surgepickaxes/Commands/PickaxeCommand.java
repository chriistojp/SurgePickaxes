package me.christo.surgepickaxes.Commands;

import me.christo.surgepickaxes.GUIs.PickaxeInventory;
import me.christo.surgepickaxes.Handlers.MongoHandler;
import me.christo.surgepickaxes.Handlers.NBTManager;
import me.christo.surgepickaxes.Handlers.Pickaxe;
import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class PickaxeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            if(args.length == 0) {
                Player player = (Player) sender;

                Pickaxe.givePickaxe(player);
                PickaxeInventory.open(player);

                Bukkit.broadcastMessage("hi" + new NBTManager(player.getItemInHand()).getNBT("jackpot", PersistentDataType.INTEGER) + "");

                for(String s :  Util.formatString(50, "", "&7Here you can do things like &6upgrade your pickaxe.", "&7Upgrading your pickaxe can help you gain", "&7resources and island value faster.", "&7Pickaxe enchantments only work on &6Ore-Field &7generated blocks.", "", "&7If you have any questions feel free", "&6to ask a staff member.")) {
                    System.out.println(s);
                }

            }
            if(args.length == 1) {
                MongoHandler.setValue(((Player) sender).getPlayer(), "level", 0);
                MongoHandler.setValue(((Player) sender).getPlayer(), "efficiency", 0);
                MongoHandler.setValue(((Player) sender).getPlayer(), "fortune", 0);
            }
            if(args.length == 2) {

            }
        }

        return false;
    }
}

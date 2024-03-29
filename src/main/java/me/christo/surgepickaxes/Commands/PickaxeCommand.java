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

                Pickaxe pickaxe = new Pickaxe(player);
                if(pickaxe.isPickaxe(player)) {
                    PickaxeInventory.open(player);
                }
                if(!Pickaxe.checkForPicaxe(player)) {
                    Pickaxe.givePickaxe(player);
                }



                for(String s :  Util.formatString(50, "", "&7Here you can do things like &6upgrade your pickaxe.", "&7Upgrading your pickaxe can help you gain", "&7resources and island value faster.", "&7Pickaxe enchantments only work on &6Ore-Field &7generated blocks.", "", "&7If you have any questions feel free", "&6to ask a staff member.")) {
                    System.out.println(s);
                }

            }
            if(args.length == 1) {
                if(args[0].equals("reset")) {
                    MongoHandler.setValue(((Player) sender).getPlayer(), "level", 0);
                    MongoHandler.setValue(((Player) sender).getPlayer(), "efficiency", 0);
                    MongoHandler.setValue(((Player) sender).getPlayer(), "fortune", 0);
                    MongoHandler.setValue(((Player) sender).getPlayer(), "gemfinder", 0);
                    MongoHandler.setValue(((Player) sender).getPlayer(), "jackpot", 0);
                    MongoHandler.setValue(((Player) sender).getPlayer(), "rampage", 0);
                    MongoHandler.setValue(((Player) sender).getPlayer(), "greed", 0);
                    MongoHandler.setValue(((Player) sender).getPlayer(), "shatterproof", 0);

                    NBTManager manager = new NBTManager(((Player) sender).getItemInHand());
                    manager.setNBT("level", PersistentDataType.INTEGER, 0);
                    manager.setNBT("efficiency", PersistentDataType.INTEGER, 0);
                    manager.setNBT("fortune", PersistentDataType.INTEGER, 0);
                    manager.setNBT("gemfinder", PersistentDataType.INTEGER, 0);
                    manager.setNBT("greed", PersistentDataType.INTEGER, 0);
                    manager.setNBT("jackpot", PersistentDataType.INTEGER, 0);
                    manager.setNBT("rampage", PersistentDataType.INTEGER, 0);
                    manager.setNBT("shatterproof", PersistentDataType.INTEGER, 0);
                }
                if(args[0].equals("give")) {
                    Pickaxe.givePickaxe(((Player) sender).getPlayer());
                }
                if(args[0].equals("level")) {
                    NBTManager manager = new NBTManager(((Player) sender).getItemInHand());
                    manager.setNBT("level", PersistentDataType.INTEGER, 5);
                    MongoHandler.setValue(((Player) sender).getPlayer(), "level", 5);

                }
            }
            if(args.length == 2) {

            }
        }

        return false;
    }
}

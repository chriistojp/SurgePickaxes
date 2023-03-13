package me.christo.surgepickaxes.Enchantments;

import me.christo.surgepickaxes.Handlers.NBTManager;
import me.christo.surgepickaxes.Main;
import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class EnchantManager {


    public static void checkEnchants(Player p) {

        NBTManager manager = new NBTManager(p.getItemInHand());
        int gemFinderLevel = manager.getNBT("gemfinder", PersistentDataType.INTEGER);
        Bukkit.broadcastMessage("gem finder level" + gemFinderLevel);
        int jackpotLevel = manager.getNBT("gemfinder", PersistentDataType.INTEGER);

        if(GemFinder.getProc(gemFinderLevel)) {
            int gems = GemFinder.generateRandomNumber();
            Util.sendMessage(p, Main.getInstance().getConfig().getString("messages.gemsFound").replaceAll("%gems%", gems + ""));
            //give player gems and send message
        }
        if(Jackpot.getProc(jackpotLevel)) {
            String command = Jackpot.getJackpotItem(jackpotLevel);
            //execute command
        }


    }

}

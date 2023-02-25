package me.christo.surgepickaxes.Enchantments;

import me.christo.surgepickaxes.Handlers.NBTManager;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class EnchantManager {


    public static void checkEnchants(Player p) {

        NBTManager manager = new NBTManager(p.getItemInHand());
        int gemFinderLevel = manager.getNBT("gemfinder", PersistentDataType.INTEGER);
        int jackpotLevel = manager.getNBT("gemfinder", PersistentDataType.INTEGER);

        if(GemFinder.getProc(gemFinderLevel)) {
            int gems = GemFinder.generateRandomNumber();
            //give player gems and send message
        }
        if(Jackpot.getProc(jackpotLevel)) {
            String command = Jackpot.getJackpotItem(jackpotLevel);
            //execute command
        }


    }

}

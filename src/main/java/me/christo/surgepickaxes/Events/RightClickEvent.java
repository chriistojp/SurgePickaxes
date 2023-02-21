package me.christo.surgepickaxes.Events;

import me.christo.surgepickaxes.Main;
import me.christo.surgepickaxes.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.concurrent.atomic.AtomicInteger;


public class RightClickEvent implements Listener {


    String[] titles = {"&fUnbreaking &7←&r &aEfficiency &7→&r &fFortune", "&fEfficiency &7←&r &aFortune &7→&r &fUnbreaking", "&fFortune &7←&r &aUnbreaking &7→&r &fEfficiency"};
    String[] keys = {"Efficiency", "Fortune", "Unbreaking"};
    int index = 0;
    boolean elligible = false;

    Long time;

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType() == Material.BAMBOO) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Player player = e.getPlayer();

                Long clickTime;


                if (e.getPlayer().isSneaking()) {
                    
                    time = System.currentTimeMillis();

                    index = (index + 1) % titles.length;
                    player.sendTitle("", Util.color(titles[index]), 0, 20, 10);

                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);



                } else {

                    clickTime = System.currentTimeMillis();

                    if(clickTime - time < 1000) {


                        AtomicInteger secondsLeft = new AtomicInteger(5);
                        int taskID = Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {

                            player.sendTitle("", Util.color("&a&l" + keys[index] + " &factivated for &a" + secondsLeft + "&f seconds!"));
                            secondsLeft.getAndDecrement();

                        }, 0, 20);
                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {

                            Main.getInstance().getServer().getScheduler().cancelTask(taskID);
                            player.sendTitle("", Util.color("&a&l" + keys[index] + " &fhas ended!"), 3, 15, 10);

                        }, 100);

                    }



                }


            }
        }
    }

}

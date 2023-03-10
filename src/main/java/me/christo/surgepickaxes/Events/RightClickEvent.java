package me.christo.surgepickaxes.Events;

import me.christo.surgecooldowns.Managers.CooldownManager;
import me.christo.surgepickaxes.Enchantments.Greed;
import me.christo.surgepickaxes.Enchantments.Rampage;
import me.christo.surgepickaxes.Handlers.Pickaxe;
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

    String[] titles = {"&fRampage &7→&r &aGreed", "&fGreed &7→&r &aRampage"};
    String[] keys = {"Greed", "Rampage"};
    int index = 0;
    boolean enchantmentActive = false;

    CooldownManager manager = new CooldownManager();
    String cooldownType = "enchantCooldown";

    Long time;

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType() == Material.DIAMOND_PICKAXE && !enchantmentActive) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Pickaxe pickaxe = new Pickaxe(e.getPlayer());
                if (pickaxe.isPickaxe(e.getPlayer())) {
                    Player player = e.getPlayer();

                    Long clickTime;

                    if (e.getPlayer().isSneaking()) {
                        time = System.currentTimeMillis();

                        index = (index + 1) % titles.length;
                        player.sendTitle("", Util.color(titles[index]), 0, 20, 10);

                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, 1);

                    } else {
                        clickTime = System.currentTimeMillis();

                        if (clickTime - time < 1000 && !manager.isOnCooldown(player, cooldownType)) {
                            enchantmentActive = true;

                            AtomicInteger secondsLeft = new AtomicInteger(10);
                            int taskID = Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                                player.sendTitle("", Util.color("&a&l" + keys[index] + " &factivated for &a" + secondsLeft + "&f seconds!"));
                                if(keys[index].equals("Rampage")) {
                                    Rampage.enableHaste(player);
                                }
                                if(keys[index].equals("Greed")) {
                                    Greed.enable(player);
                                }
                                secondsLeft.getAndDecrement();
                            }, 0, 20);


                            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                                Main.getInstance().getServer().getScheduler().cancelTask(taskID);
                                player.sendTitle("", Util.color("&a&l" + keys[index] + " &fhas ended!"), 3, 15, 10);

                                if(keys[index].equals("Greed")) {
                                    Greed.disable(player);
                                }

                                manager.setCooldown(player, cooldownType, 5); // set cooldown
                                enchantmentActive = false;
                            }, 200);

                        } else if (manager.isOnCooldown(player, cooldownType)) {
                            player.sendTitle(Util.color("&c&lCOOLDOWN"), Util.color("&c") + manager.getTimeLeft(player, cooldownType) + " &fseconds remaining!");
                        }
                    }
                }
            } else if (enchantmentActive && e.getPlayer().isSneaking() && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                e.setCancelled(true);
            }
        }
    }
}

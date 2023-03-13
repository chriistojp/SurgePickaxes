package me.christo.surgepickaxes;



import me.christo.surgepickaxes.Commands.PickaxeCommand;
import me.christo.surgepickaxes.Events.BlockBreak;
import me.christo.surgepickaxes.Events.JoinEvent;
import me.christo.surgepickaxes.Events.LossPrevention.PickaxeListener;
import me.christo.surgepickaxes.Events.RightClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
            saveDefaultConfig();

        //pasword 1BHVHJ1oKtIicI7l

        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new RightClickEvent(), this);
        getCommand("pickaxe").setExecutor(new PickaxeCommand());
        this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        this.getServer().getPluginManager().registerEvents(new PickaxeListener(), this);






        // Close the connection


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

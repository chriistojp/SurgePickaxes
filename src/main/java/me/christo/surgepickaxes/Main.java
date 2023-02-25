package me.christo.surgepickaxes;



import me.christo.surgepickaxes.Commands.PickaxeCommand;
import me.christo.surgepickaxes.Events.JoinEvent;
import me.christo.surgepickaxes.Events.RightClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
//        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
//        mongoLogger.setLevel(Level.SEVERE);
//
//        Logger rootLogger = Logger.getLogger("");
//        rootLogger.setLevel(Level.OFF);

        //pasword 1BHVHJ1oKtIicI7l

        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new RightClickEvent(), this);
        getCommand("pickaxe").setExecutor(new PickaxeCommand());
        this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);






        // Close the connection


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

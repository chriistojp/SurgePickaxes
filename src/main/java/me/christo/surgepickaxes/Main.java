package me.christo.surgepickaxes;

import me.christo.surgepickaxes.Events.RightClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new RightClickEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

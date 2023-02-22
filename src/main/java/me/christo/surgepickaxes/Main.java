package me.christo.surgepickaxes;

import me.christo.surgepickaxes.Commands.PickaxeCommand;
import me.christo.surgepickaxes.Events.RightClickEvent;
//import me.christo.surgepickaxes.Handlers.MongoHandler;
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
        getCommand("pickaxe").setExecutor(new PickaxeCommand());

      //  MongoHandler handler = new MongoHandler();
        //handler.connect("mongodb+srv://christopayne78:chr1st0mongo3042!!@@@pickaxes.4ikq1d7.mongodb.net/?retryWrites=true&w=majority", "Pickaxes");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

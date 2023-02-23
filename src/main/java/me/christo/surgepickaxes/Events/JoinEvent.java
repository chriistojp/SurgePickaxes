package me.christo.surgepickaxes.Events;

import me.christo.surgepickaxes.Handlers.MongoHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        if(!MongoHandler.checkIfDocumentExists(player)) {
            MongoHandler.createNewDocument(player);
        }


    }
}

package me.tud.diskuise.listeners;

import me.tud.diskuise.Diskuise;
import me.tud.diskuise.utils.UpdateChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UpdateChecker updateChecker = new UpdateChecker(Diskuise.getInstance(), Diskuise.getResourceId());
        updateChecker.checkForUpdates(event.getPlayer());
    }

}

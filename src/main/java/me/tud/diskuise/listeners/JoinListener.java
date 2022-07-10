package me.tud.diskuise.listeners;

import me.tud.diskuise.Diskuise;
import me.tud.diskuise.util.UpdateChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void updateChecker(PlayerJoinEvent event) {
        UpdateChecker updateChecker = Diskuise.getInstance().getUpdateChecker();
        updateChecker.checkForUpdates(event.getPlayer());
    }

}

package me.tud.diskuise.elements.entities.player.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Player Disguise - Make Display In Tab")
@Description("Set whether a player disguise is displayed in tab")
@Examples("make player's disguise display in tab")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class EffDisplayInTab extends WatcherMakeEffect<PlayerWatcher> {

    static {
        register(EffDisplayInTab.class, "[:not] display[ed] in tab");
    }

    @Override
    protected String getProperty() {
        return "display in tab";
    }

    @Override
    protected void make(Event e, PlayerWatcher playerWatcher, boolean bool) {
        playerWatcher.setDisplayedInTab(bool);
    }
}

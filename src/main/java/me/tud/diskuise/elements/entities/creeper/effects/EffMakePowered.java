package me.tud.diskuise.elements.entities.creeper.effects;

import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

public class EffMakePowered extends WatcherMakeEffect<CreeperWatcher> {

    static {
        register(EffMakePowered.class, "[:not] powered");
    }

    @Override
    protected String getProperty() {
        return "powered";
    }

    @Override
    protected void make(Event e, CreeperWatcher creeperWatcher, boolean state) {
		creeperWatcher.setPowered(state);
    }

}

package me.tud.diskuise.elements.entities.creeper.effects;

import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

public class EffMakeIgnited extends WatcherMakeEffect<CreeperWatcher> {

    static {
        register(EffMakeIgnited.class, "[:not] ignited");
    }

    @Override
    protected String getProperty() {
        return "ignited";
    }

    @Override
    protected void make(Event e, CreeperWatcher creeperWatcher, boolean state) {
		creeperWatcher.setIgnited(state);
    }

}

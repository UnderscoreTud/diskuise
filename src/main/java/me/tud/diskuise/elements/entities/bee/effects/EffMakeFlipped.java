package me.tud.diskuise.elements.entities.bee.effects;

import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

public class EffMakeFlipped extends WatcherMakeEffect<BeeWatcher> {

    static {
        register(EffMakeFlipped.class, "[:not] flipped");
    }

    @Override
    protected String getProperty() {
        return "flipped";
    }

    @Override
    protected void make(Event e, BeeWatcher beeWatcher, boolean state) {
        beeWatcher.setFlipped(state);
    }
}

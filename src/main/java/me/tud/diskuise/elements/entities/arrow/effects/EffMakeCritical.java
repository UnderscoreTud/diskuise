package me.tud.diskuise.elements.entities.arrow.effects;

import me.libraryaddict.disguise.disguisetypes.watchers.TippedArrowWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

public class EffMakeCritical extends WatcherMakeEffect<TippedArrowWatcher> {

    static {
        register(EffMakeCritical.class, "[:not] [a] critical [arrow]");
    }

    @Override
    protected String getProperty() {
        return "a critical arrow";
    }

    @Override
    protected void make(Event e, TippedArrowWatcher tippedArrowWatcher) {
        tippedArrowWatcher.setCritical(!isNegated());
    }
}

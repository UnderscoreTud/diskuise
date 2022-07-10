package me.tud.diskuise.elements.entities.witherskull.effects;

import me.libraryaddict.disguise.disguisetypes.watchers.WitherSkullWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

public class EffMakeBlueSkull extends WatcherMakeEffect<WitherSkullWatcher> {

    static {
        register(EffMakeBlueSkull.class, "[a] ([:not] blue|not:black) skull");
    }

    @Override
    protected String getProperty() {
        return "blue skull";
    }

    @Override
    protected void make(Event e, WitherSkullWatcher witherSkullWatcher) {
        witherSkullWatcher.setBlue(!isNegated());
    }
}

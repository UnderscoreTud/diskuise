package me.tud.diskuise.elements.entities.hostile.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Hostile Disguise - Make Enraged")
@Description("Sets whether a hostile disguise is enraged.")
@Examples("make player's disguise enraged")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class EffMakeEnraged extends WatcherMakeEffect<InsentientWatcher> {

    static {
        register(EffMakeEnraged.class, "[:not] enraged");
    }

    @Override
    protected String getProperty() {
        return "enraged";
    }

    @Override
    protected void make(Event e, InsentientWatcher insentientWatcher, boolean bool) {
        insentientWatcher.setEnraged(bool);
    }
}
